package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class SignInAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(SignInAJAXCommand.class);

	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
									throws AJAXCommandException {
		PrintWriter pw = null;
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		UserServiceObject userServiceObject = null;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AuthorizationService authorizationService = serviceFactory
														.getAuthorizationService();
		
		try{
			userServiceObject = authorizationService.singIn(login, password);
		}catch(ServiceException e){
			log.error("can't authoruze user, signInCommandError",e);
		}
		
		if(userServiceObject.getUser() != null){
			request.getSession(true).setAttribute(SessionParameterName.SESSION_USER,
												  userServiceObject.getUser());
			PrintWriteHelper.printToPrintWriter(response,
												userServiceObject.getJsonWithSuccess());	

		} else{
			PrintWriteHelper.printToPrintWriter(response, 
												userServiceObject.getJsonWithErrors());
		}
	
	}

}
