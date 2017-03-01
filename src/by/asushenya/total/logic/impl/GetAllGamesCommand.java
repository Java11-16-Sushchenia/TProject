package by.asushenya.total.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class GetAllGamesCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
				
		List<Game> games = null;
			
		try {
						
		 games = DAOFactory.getInstance().getBookMakerDAO().getAllGames();
		 System.out.println(games.get(0).getFirstTeam());
		 System.out.println("ratest count" + games.size());
		 
		 
		
		} catch (DAOException e) {
			
			e.printStackTrace();
		}		
		request.setAttribute("games", games);	
		
		return request.getParameter("go_to_page");
	}

}
