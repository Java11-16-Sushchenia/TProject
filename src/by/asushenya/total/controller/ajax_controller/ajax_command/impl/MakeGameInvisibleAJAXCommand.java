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

public class MakeGameInvisibleAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(
										MakeGameInvisibleAJAXCommand.class);
	@Override
	public void execute(HttpServletRequest request, 
						HttpServletResponse response)
									throws AJAXCommandException {
		
		int gameId = Integer.parseInt(
				request.getParameter(RequestParameterName.GAME_ID));
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookMakerService bookMakerService = serviceFactory.getBookMakerService();
		
		try{
			bookMakerService.makeGameInvisible(gameId);
		} catch(ServiceException e){
			log.error("can't make game invisible",e);
			throw new AJAXCommandException("can't make game invisible",e);
		}
	}

}
