package by.asushenya.total.logic.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.util.Encryptor;
import by.asushenya.total.logic.util.PersonalPagesHelper;

public class AddNewGameCommand implements ICommand {

	@Override 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
	
		HttpSession session = request.getSession(true);
		Game game = new Game();
		
		game.setGameKind(request.getParameter("game_kind"));//стоит ли проверять select'ы
		game.setFirstTeam(request.getParameter("team_1"));
		game.setSecondTeam(request.getParameter("team_2"));
																																							
		if(game.getFirstTeam().equals(game.getSecondTeam())){
			session.setAttribute("add_game_error", "firstequalstwo");
			return "redirectToAddNewGamePage";
		}
		
		String datetimeLocal = null; 
		Timestamp date = null;
		
		try{
			datetimeLocal = request.getParameter("date");
			datetimeLocal = datetimeLocal.concat(":00");			
			date = Timestamp.valueOf(datetimeLocal.replace("T", " "));
			
		} catch(IllegalArgumentException e){
			session.setAttribute("add_game_error", "badtime");
			return "redirectToAddNewGamePage"; 
		}

		if(date.compareTo(new java.util.Date()) <=0){
			session.setAttribute("add_game_error", "littletime");
			return "redirectToAddNewGamePage"; 
		}		
		game.setDate(date);		
		
		String k1Buffer =request.getParameter("k1");		
		if(k1Buffer.isEmpty()){
			session.setAttribute("add_game_error", "k1isempty");
			return "redirectToAddNewGamePage"; 
		}		
		double k1;
		try{
			 k1 = Double.parseDouble(k1Buffer);
		} catch(NumberFormatException e){
			session.setAttribute("add_game_error", "k1isstring");
			return "redirectToAddNewGamePage"; 
		}		
		if(k1 < 1.1D){
			session.setAttribute("add_game_error", "k1islittle");
			return "redirectToAddNewGamePage"; 
		}
		game.setK1(k1);
		
		String kxBuffer =request.getParameter("kx");		
		if(kxBuffer.isEmpty()){
			session.setAttribute("add_game_error", "kxisempty");
			return "redirectToAddNewGamePage"; 
		}
		double kx;
		try{
			 kx = Double.parseDouble(kxBuffer);
		} catch(NumberFormatException e){
			session.setAttribute("add_game_error", "kxisstring");
			return "redirectToAddNewGamePage"; 
		}		
		if(kx < 1.1D){
			session.setAttribute("add_game_error", "kxislittle");
			return "redirectToAddNewGamePage"; 
		}	
		game.setKx(kx);
		
		String k2Buffer =request.getParameter("k2");		
		if(k1Buffer.isEmpty()){
			session.setAttribute("add_game_error", "k2isempty");
			return "redirectToAddNewGamePage"; 
		}
		double k2;
		try{
			 k2 = Double.parseDouble(k2Buffer);
		} catch(NumberFormatException e){
			session.setAttribute("add_game_error", "k2isstring");
			return "redirectToAddNewGamePage"; 
		}		
		if(k2 < 1.1D){
			session.setAttribute("add_game_error", "k2islittle");
			return "redirectToAddNewGamePage"; 
		}	
		game.setKx(k2);
		
		try {
			DAOFactory.getInstance().getBookMakerDAO().addGame(game);
		} catch (DAOException e) {
			//log
			e.printStackTrace();
		}		
		
		return "redirectToBookmakerPage";
	}
}
