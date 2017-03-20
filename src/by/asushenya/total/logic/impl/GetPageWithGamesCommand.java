package by.asushenya.total.logic.impl;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class GetPageWithGamesCommand implements ICommand{


	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		int page = 1;
		int recordsPerPage = 5;
		int noOfRecords = 0;
		GameKind gameKind =  null;
		String local = (String)request.getSession(true).getAttribute("local");
		
		
		List<Game> list = null;
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}
		
		if(request.getParameter(RequestParameterName.PAGE_NUMBER) != null){
			page = Integer.parseInt(request.getParameter(
						RequestParameterName.PAGE_NUMBER));
		}
		
		if(request.getParameter(RequestParameterName.GAME_KIND) != null && 
			!request.getParameter(RequestParameterName.GAME_KIND).isEmpty()	){
			
			gameKind = GameKind.valueOf(request.getParameter(
							RequestParameterName.GAME_KIND).toUpperCase());
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try{
			 list = userDAO.getGamesForPage((page-1)*recordsPerPage,
					 						 recordsPerPage,
					 						 gameKind,
					 						 local);

			 noOfRecords = userDAO.getGamesRecordsByGameKindCount(gameKind);
		} catch(DAOException e){
			e.printStackTrace();
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		request.setAttribute("games", list);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		
		if(gameKind == null){
			request.setAttribute(RequestParameterName.GAME_KIND,
					 null);
		}else{
			request.setAttribute(RequestParameterName.GAME_KIND,
					 gameKind.toString());
		}
		

		return request.getParameter("go_to_page");
	}

}
