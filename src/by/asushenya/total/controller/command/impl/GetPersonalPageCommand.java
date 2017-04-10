package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;
import by.asushenya.total.service.util.PersonalPagesHelper;

public class GetPersonalPageCommand implements ICommand {
	
	private static final Logger log = Logger.getLogger(GetPersonalPageCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		User user = (User) request.getSession(true).getAttribute(SessionAttributeName.SESSION_USER);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AuthorizationService authService = serviceFactory.getAuthorizationService();
		
		try {
			authService.refreshUserData(user);
		} catch (ServiceException e) {
			log.error("can't refresh user data",e);
			throw new CommandException("can't refresh user data",e);
		}
		
		return PersonalPagesHelper.getInstance().getPersonalPage(user.getRole());
	}

}
