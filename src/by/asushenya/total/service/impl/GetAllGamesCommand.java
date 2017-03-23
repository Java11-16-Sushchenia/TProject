package by.asushenya.total.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.CommandException;
import by.asushenya.total.service.ICommand;

public class GetAllGamesCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
				
		/*List<Game> games = null;
			
		try {
						
		 games = DAOFactory.getInstance().getBookMakerDAO().getAllGames(RequestParameterName.SESSION_LOCAL_EN);
		 System.out.println(games.get(0).getFirstTeam());
		 System.out.println("ratest count" + games.size());	
		
		} catch (DAOException e) {
			
			e.printStackTrace();
		}		
		request.setAttribute("games", games);	*/ 
		
		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
