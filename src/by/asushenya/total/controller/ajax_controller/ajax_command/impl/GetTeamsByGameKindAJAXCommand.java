package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.dao.impl.BookMakerDAOImpl;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class GetTeamsByGameKindAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(
										GetTeamsByGameKindAJAXCommand.class);
	
	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
								throws AJAXCommandException {
		
		List<Team> listOfTeams = null;
		String gameKindAtString = request.getParameter(RequestParameterName.GAME_KIND);
		String local = (String)request.getSession(true).getAttribute("local");
		GameKind gameKind = null;
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		if(!gameKindAtString.isEmpty()){
			gameKind = GameKind.valueOf(gameKindAtString.toUpperCase());
		}
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		
		try{			
			listOfTeams = adminService.getTeamsByGameKind(gameKind, local);
		} catch(ServiceException e){
			log.error("can't get page with users",e);
		}
		
		JSONArray teamsArray = new JSONArray();
		for(Team team: listOfTeams){
			teamsArray.add(team.getName());
		}		
		returnTeamsListToClient(response, teamsArray);
	}
	
	private static void returnTeamsListToClient(HttpServletResponse response,
												JSONArray teams){
		
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.TEAMS_ARRAY, teams);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(json.toString());
		} catch (IOException e) {		
			e.printStackTrace();
		} finally{
			pw.close();
		}
		
	}

}
