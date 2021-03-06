package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.PersonalPagesHelper;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * If user click to "Go to personal page" this command refresh user data form
 * data source and return user page url.
 * 
 * @author Artyom Sushenya
 *
 */
public class GetPersonalPageCommand implements ICommand {

	private static final Logger log = Logger.getLogger(GetPersonalPageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		User user = (User) request.getSession(true).getAttribute(SessionAttributeName.SESSION_USER);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AuthorizationService authService = serviceFactory.getAuthorizationService();

		try {
			User refreshedUser;
			refreshedUser = authService.refreshUserData(user);
			request.getSession(true).setAttribute(SessionAttributeName.SESSION_USER, refreshedUser);
		} catch (ServiceException e) {
			log.error("can't refresh user data", e);
			throw new CommandException("can't refresh user data", e);
		}

		return PersonalPagesHelper.getInstance().getPersonalPage(user.getRole());
	}

}
