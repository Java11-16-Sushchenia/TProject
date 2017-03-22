package by.asushenya.total.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.CommandException;
import by.asushenya.total.service.ICommand;

public class GetGamesByKindCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
				return null;
				//DEPRICATED
	/*	List<Game> games = null;
			
		try {
		GameKind gameType =GameKind.valueOf(
							request.getParameter("game_kind")
							.toUpperCase());		
			
		 games = DAOFactory.getInstance().getUserDAO().getGamesByType(gameType);
		
		
		} catch (DAOException e) {
			
			e.printStackTrace();
		}				
		request.setAttribute("games", games);	
		request.setAttribute("game_kind", 
				request.getParameter("game_kind").toUpperCase());		
		return request.getParameter("go_to_page");*/
	}

}
