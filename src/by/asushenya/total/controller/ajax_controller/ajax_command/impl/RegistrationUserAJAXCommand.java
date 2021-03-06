package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.Encryptor;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command extract {@link User} data from ajax request and register new
 * {@link User} at system
 * 
 * @author Artyom Asushenya
 *
 */
public class RegistrationUserAJAXCommand implements IAJAXCommand {

	private static final Logger log = Logger.getLogger(RegistrationUserAJAXCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		String login = request.getParameter(RequestParameterName.LOGIN);
		String email = request.getParameter(RequestParameterName.USER_EMAIL);
		String password = Encryptor.getMD5Hash(request.getParameter(RequestParameterName.PASSWORD));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String serviceResponse = null;

		try {
			serviceResponse = userService.registrationUser(login, email, password);

		} catch (ServiceException e) {
			log.error("can't register user", e);
		}

		PrintWriteHelper.printToPrintWriter(response, serviceResponse);

	}

}
