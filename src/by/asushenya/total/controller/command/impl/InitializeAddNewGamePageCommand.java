package by.asushenya.total.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command put at request list of football {@link Team}s. It's a
 * initialization of game add page.
 * 
 * @author Artyom Sushenya
 *
 */
public class InitializeAddNewGamePageCommand implements ICommand {
	private static final Logger log = Logger.getLogger(InitializeAddNewGamePageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		List<Team> listOfTeams = null;
		String local = (String) request.getSession(true).getAttribute(SessionAttributeName.LOCAL);

		if (local == null) {
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			listOfTeams = adminService.getTeamsByGameKind(GameKind.FOOTBALL, local);
		} catch (ServiceException e) {
			log.error("can't get football teams", e);
		}

		request.setAttribute(RequestParameterName.TEAMS, listOfTeams);

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
