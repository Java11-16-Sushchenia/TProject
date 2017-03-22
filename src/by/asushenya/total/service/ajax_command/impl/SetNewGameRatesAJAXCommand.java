package by.asushenya.total.service.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.ajax_command.AJAXCommandException;
import by.asushenya.total.service.ajax_command.IAJAXCommand;

public class SetNewGameRatesAJAXCommand implements IAJAXCommand{


	public void execute(HttpServletRequest request, HttpServletResponse response) 
													throws AJAXCommandException {
		
		int gameId = Integer.parseInt(
				request.getParameter(RequestParameterName.GAME_ID));
		double k1 = Double.parseDouble(
				request.getParameter(RequestParameterName.K1));
		double kx = Double.parseDouble(
				request.getParameter(RequestParameterName.KX));
		double k2 = Double.parseDouble(
				request.getParameter(RequestParameterName.K2));
		
		

		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();
		
		try{
			bookMakerDAO.setNewGameRates(gameId, k1, kx, k2);
		} catch(DAOException e){
			//log error
		}
		
	}

}
