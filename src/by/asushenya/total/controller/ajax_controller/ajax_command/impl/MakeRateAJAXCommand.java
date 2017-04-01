package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;
import by.asushenya.total.service.impl.UserServiceImpl;
import sun.security.util.AuthResources_zh_HK;

public class MakeRateAJAXCommand implements IAJAXCommand {

	private static final Logger log = Logger.getLogger(MakeRateAJAXCommand.class);

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		int gameId = Integer.parseInt(request.getParameter(RequestParameterName.GAME_ID));
		User user = (User) request.getSession(true).getAttribute(SessionParameterName.SESSION_USER);
		RateChoice choice = RateChoice.valueOf(request.getParameter(RequestParameterName.CHOICE));
		double rateCoefficient = Double.parseDouble(request.getParameter(RequestParameterName.RATE_COEFFICIENT));
		double rateMoney = Double.parseDouble(request.getParameter(RequestParameterName.RATE_MONEY));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String serviceResponse = null;

		try {
			serviceResponse = userService.makeRate(gameId, user, choice, rateCoefficient, rateMoney);
		} catch (ServiceException e) {
			log.error("can't make rate", e);
		}
		
		/*try{
			User currentUser = (User)request.getSession(true).getAttribute("user");
		UserServiceObject refreshedUser =	ServiceFactory.getInstance().getAuthorizationService().singIn(currentUser.getLogin(), currentUser.getPassword());
			request.getSession(true).setAttribute("user", refreshedUser.getUser());
		} catch(ServiceException e){
			log.error("can't reauthorize user",e);
		}*/

		PrintWriteHelper.printToPrintWriter(response, serviceResponse);
	}

}
