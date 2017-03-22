package by.asushenya.total.logic.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.ajax_command.AJAXCommandException;
import by.asushenya.total.logic.ajax_command.IAJAXCommand;

public class GetTeamsOfSomeGameKindAJAXCommand implements IAJAXCommand{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {
		
		List<Team> listOfTeams = null;
		String gameKindAtString = request.getParameter("gameKind");
		String local = (String)request.getSession(true).getAttribute("local");
		GameKind gameKind = null;
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		if(!gameKindAtString.isEmpty()){
			gameKind = GameKind.valueOf(gameKindAtString.toUpperCase());
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try{
			listOfTeams = userDAO.getTeamsByGameKind(gameKind, local);
			
		} catch(DAOException e){
			//log e
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
			
			//System.out.println(json.toString());
			response.setCharacterEncoding("utf-8");
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			} 
			pw.print(json.toString());
			pw.close();
	}

}
