package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command put at ajax response {@link Team}s of some {@link GameKind}
 * 
 * @author Artyom Asushenya
 *
 */
public class GetTeamsByGameKindAJAXCommand implements IAJAXCommand {

	private static final Logger log = Logger.getLogger(GetTeamsByGameKindAJAXCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		List<Team> listOfTeams = null;
		HashMap<String, Object> jsonInfo = new HashMap<String, Object>();
		String gameKindAtString = request.getParameter(RequestParameterName.GAME_KIND);
		String local = (String) request.getSession(true).getAttribute("local");
		GameKind gameKind = null;

		if (local == null) {
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		if (!gameKindAtString.isEmpty()) {
			gameKind = GameKind.valueOf(gameKindAtString.toUpperCase());
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			listOfTeams = adminService.getTeamsByGameKind(gameKind, local);
		} catch (ServiceException e) {
			log.error("can't get page with users", e);
		}

		List<String> teamsArray = new ArrayList<String>();
		for (Team team : listOfTeams) {
			teamsArray.add(team.getName());
		}

		jsonInfo.put(ResponseParameterName.TEAMS_ARRAY, teamsArray);
		JSONObject json = new JSONObject(jsonInfo);

		PrintWriteHelper.printToPrintWriter(response, json.toString());
	}

}
