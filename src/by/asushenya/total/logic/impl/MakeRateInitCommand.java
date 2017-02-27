package by.asushenya.total.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class MakeRateInitCommand implements ICommand{


	public String execute(HttpServletRequest request) throws CommandException {
		
		HttpSession session = request.getSession();

		session.setAttribute("game_id", request.getParameter("game_id"));
		session.setAttribute("first_team", request.getParameter("first_team"));
		session.setAttribute("second_team", request.getParameter("second_team"));
		session.setAttribute("rate_koefficient", request.getParameter("rate_koefficient"));
		session.setAttribute("choice", request.getParameter("choice"));
		session.setAttribute("game_kind", request.getParameter("game_king"));

	if( request.getParameter("game_kind").equals("football")){
		return "redirectToIndexWithFootballGame";
	}
	if( request.getParameter("game_kind").equals("basketball")){
		return "redirectToIndexWithBasketballGame";
	}
	if( request.getParameter("game_kind").equals("hockey")){
		return "redirectToIndexWithHockeyGame";
	}
	
	return "redirectToIndexPage";
}
	
}
