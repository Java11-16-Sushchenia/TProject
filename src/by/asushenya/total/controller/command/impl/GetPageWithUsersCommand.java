package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command put list {@link User} and list size at request.
 * 
 * @author Artyom Sushenya
 *
 */
public class GetPageWithUsersCommand implements ICommand {

	private static final Logger log = Logger.getLogger(GetPageWithUsersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		int page = 1;
		int recordsPerPage = 5;
		UsersPage pageWithUsers = new UsersPage();

		if (request.getParameter(RequestParameterName.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			pageWithUsers = adminService.getUsersPage(page, recordsPerPage);
		} catch (ServiceException e) {
			log.error("can't get page with users", e);
		}

		request.setAttribute("users", pageWithUsers.getUsersList());
		request.setAttribute("noOfPages", pageWithUsers.getNumberOfPages());
		request.setAttribute("currentPage", page);

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
