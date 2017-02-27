package by.asushenya.total.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class GetAllTeamsCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
				
		List<Team> teams = null;
			
		try {
						
		 teams = DAOFactory.getInstance().getBookMakerDAO().getAllTeams();
		 System.out.println(teams.get(0).getName());
		 System.out.println("ratest count" + teams.size());
		
		} catch (DAOException e) {
			
			e.printStackTrace();
		}		
		request.setAttribute("teams", teams);	
		
		return request.getParameter("go_to_page");
	}

}
