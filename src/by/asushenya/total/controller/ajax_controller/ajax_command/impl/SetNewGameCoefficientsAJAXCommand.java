package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class SetNewGameCoefficientsAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(
					SetNewGameCoefficientsAJAXCommand.class);

	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
								throws AJAXCommandException {
		
		System.out.println(request.getSession(true).getId());
		
		int gameId = Integer.parseInt(
				request.getParameter(RequestParameterName.GAME_ID));
		double k1 = Double.parseDouble(
				request.getParameter(RequestParameterName.K1));
		double kx = Double.parseDouble(
				request.getParameter(RequestParameterName.KX));
		double k2 = Double.parseDouble(
				request.getParameter(RequestParameterName.K2));
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookMakerService bookMakerService = serviceFactory.getBookMakerService();
		
		try{
			bookMakerService.setNewGameCoefficients(gameId, k1, kx, k2);
		} catch(ServiceException e){
			log.error("can't set new game coefficients at command",e);
			throw new AJAXCommandException("can't set game coefficients",e);
		}
	}

}
