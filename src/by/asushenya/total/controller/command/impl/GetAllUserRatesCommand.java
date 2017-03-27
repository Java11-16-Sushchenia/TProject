package by.asushenya.total.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.controller.SessionParameterName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class GetAllUserRatesCommand implements ICommand{

	private static final Logger log = Logger.getLogger(GetAllUserRatesCommand.class);
	
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws CommandException {
		
		User user = (User) request.getSession()
								  .getAttribute(
										  SessionParameterName.SESSION_USER);
		List<Rate> rates = null;
		
		//System.out.println("Получение ставок пользователя"+user.getLogin());
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		
		try {			
			 rates = userService.getAllUserRates(user);
			 request.setAttribute("rates", rates);	
			 return "userPage";
		} catch (ServiceException e) {
			log.error("can't get user rates form services",e);
		}
		
		System.out.println("returned userPage");
		return "userPage";
		
		
	}

}
