package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;
import by.asushenya.total.service.impl.UserServiceImpl;

public class MakeRateAJAXCommand implements IAJAXCommand{
	
	private static final Logger log = Logger.getLogger(
									MakeRateAJAXCommand.class);

	@Override
	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
									throws AJAXCommandException {
		
		int gameId =Integer.parseInt(request.getParameter(
										RequestParameterName.GAME_ID));
		User user = (User)request.getSession(true).getAttribute(
										SessionParameterName.SESSION_USER);		
		String choice = request.getParameter(
										RequestParameterName.CHOICE);
		double rateCoefficient = Double.parseDouble(request.getParameter(
										RequestParameterName.RATE_COEFFICIENT));
		double rateMoney = Double.parseDouble(request.getParameter(
										RequestParameterName.RATE_MONEY));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String serviceResponse = null;
		try{
			serviceResponse = userService.makeRate(gameId, 
													 user,
													 choice,
													 rateCoefficient,
													 rateMoney);
		} catch(ServiceException e){
			log.error("can't make rate",e);
		}
		
		PrintWriteHelper.printToPrintWriter(response, serviceResponse);
	}

}
