package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;
import sun.security.util.AuthResources_zh_HK;

public class SignInAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(SignInAJAXCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		User authorizedUser = null;
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AuthorizationService authorizationService = serviceFactory.getAuthorizationService();
		try{
			authorizedUser = authorizationService.singIn(login, password);
		}catch(ServiceException e){
			log.error("can't authoruze user, signInCommandError",e);
		}
		
		if(authorizedUser != null){
			request.getSession(true).setAttribute("user", authorizedUser);
		}
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {		
			e.printStackTrace();
		} 
		pw.print("sig in ajax command");
		pw.close();		
	}
	
	private static String returnSuccessToClient(String userRole){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);
		json.put(ResponseParameterName.USER_ROLE, userRole);
		
		return json.toString();
	}
}
