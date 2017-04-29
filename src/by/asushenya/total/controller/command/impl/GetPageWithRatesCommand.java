package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.RatesPage;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command put list {@link Rate} and list size at request.
 * 
 * @author Artyom Sushenya
 *
 */
public class GetPageWithRatesCommand implements ICommand {

	private static final Logger log = Logger.getLogger(GetPageWithRatesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		int page = 1;
		int recordsPerPage = 5;
		User user = (User) request.getSession(true).getAttribute(SessionAttributeName.SESSION_USER);
		String local = (String) request.getSession(true).getAttribute(SessionAttributeName.LOCAL);

		RatesPage pageWithUsers = new RatesPage();

		if (request.getParameter(RequestParameterName.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
		}

		if (local == null) {
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			pageWithUsers = userService.getRatesPage(user, page, recordsPerPage, local);
		} catch (ServiceException e) {
			log.error("can't get page with rates", e);
		}

		request.setAttribute(RequestParameterName.RATES, pageWithUsers.getRatesList());
		request.setAttribute(RequestParameterName.NUMBER_OF_PAGES, pageWithUsers.getNumberOfPages());
		request.setAttribute(RequestParameterName.CURRENT_PAGE, page);

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}
}
