package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class AddNewGameAJAXCommand implements IAJAXCommand {
	private static final Logger log = Logger.getLogger(AddNewGameAJAXCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		GameKind gameKind = GameKind.valueOf(request.getParameter(RequestParameterName.GAME_KIND).toUpperCase());
		String firstTeamName = request.getParameter(RequestParameterName.FIRST_TEAM);
		String secondTeamName = request.getParameter(RequestParameterName.SECOND_TEAM);
		Timestamp gameDate = Timestamp.valueOf(request.getParameter(RequestParameterName.GAME_DATE));
		

		double k1 = Double.parseDouble(request.getParameter(RequestParameterName.K1));
		double kx = Double.parseDouble(request.getParameter(RequestParameterName.KX));
		double k2 = Double.parseDouble(request.getParameter(RequestParameterName.K2));
		String local = (String) request.getSession(true).getAttribute("local");

		String serviceResponse = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			serviceResponse = adminService.addNewGame(gameKind, firstTeamName, secondTeamName, gameDate, k1, kx, k2,
					local);
		} catch (ServiceException e) {
			log.error("can't add new game", e);
			throw new AJAXCommandException("can't add new game", e);
		}

		PrintWriteHelper.printToPrintWriter(response, serviceResponse);

	}

}
