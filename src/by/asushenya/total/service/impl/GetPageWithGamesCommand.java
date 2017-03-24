package by.asushenya.total.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetPageWithGamesCommand {

	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  {
		/*--------Depricated----------*/
		/*int page = 1;
		int recordsPerPage = 5;
		int noOfRecords = 0;
		GameKind gameKind =  null;
		//заменить на всртоенный класс Local
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
			
			String gameKindInString = request.getParameter(RequestParameterName.GAME_KIND);
			gameKind = GameKind.valueOf(gameKindInString.toUpperCase());
			System.out.println(gameKind);
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
		}*/

		return null;
	}

}
