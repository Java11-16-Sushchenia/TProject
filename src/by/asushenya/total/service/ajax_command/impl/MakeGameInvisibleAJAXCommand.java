package by.asushenya.total.service.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.ajax_command.AJAXCommandException;
import by.asushenya.total.service.ajax_command.IAJAXCommand;

public class MakeGameInvisibleAJAXCommand implements IAJAXCommand{


	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {
		
		int gameId = Integer.parseInt(
				request.getParameter(RequestParameterName.GAME_ID));
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();
		System.out.println("invisibled: "+gameId);
		try{
			bookMakerDAO.makeGameInvisible(gameId);
		} catch(DAOException e){
			//log error
		}
	}

}
