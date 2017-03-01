package by.asushenya.total.logic.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.media.sound.SoftTuning;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.util.Encryptor;
import by.asushenya.total.logic.util.PersonalPagesHelper;

public class MakeRateCommand implements ICommand {

	@Override 
	public String execute(HttpServletRequest request) throws CommandException{
	
		HttpSession session = request.getSession(true);
		
		String game_id = request.getParameter("game_id");
		String rate_money = request.getParameter("rate_money");
		String rate_coefficient = request.getParameter("rate_koefficient");
		String choice = request.getParameter("choice");
		String gameKind = request.getParameter("game_kind");
				
		Rate rate = new Rate();	
		
		Game game = new Game();		
		
		if(game_id == null || game_id.isEmpty()){
			session.setAttribute("makeRateError", "unknownerror");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;
		}
		
		game.setId(Integer.parseInt(game_id));
		rate.setGame(game);
		
		User user =(User) request.getSession(true).getAttribute("user");		
		
		if(user == null){
			session.setAttribute("makeRateError", "emptyuser");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;
		}
		
		rate.setUser(user);
		
		if(rate_money == null || rate_money.isEmpty()){
			session.setAttribute("makeRateError", "emptyrate");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;			
		}
		
		Double rateValue = null;
		rateValue = Double.parseDouble(rate_money);
		
		if(rateValue == null){			
			session.setAttribute("makeRateError", "ratenotanumber");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;
		}
		
		if(rateValue > user.getCash()){
			session.setAttribute("makeRateError", "nomoney");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;	
		}		
		
		if(rateValue <= 0){
			session.setAttribute("makeRateError", "unknownerror");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;	
		}
		
		rate.setMoney(rateValue);
		
		if(!choice.equals("t1") && !choice.equals("x") && !choice.equals("t2")){//если выбор не t1 не x и не t2 то ошибка
			session.setAttribute("makeRateError", "unknownerror");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;	
		}
		
		rate.setChoice(choice);
		
		double rateCoefficientValue = Double.parseDouble(rate_coefficient);
		if(rateCoefficientValue <= 0){			
			session.setAttribute("makeRateError", "ratenotanumber");	
			String goToPage = getRedirectPageByGameKind(gameKind);
			return goToPage;
		}		
		
		rate.setGameCoefficient(rateCoefficientValue);

		
		try {
			DAOFactory.getInstance().getUserDAO().makeRate(rate);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
		session.removeAttribute("makeRateError");
		session.setAttribute("makeRateSuccess", "betisplaced");	
		
		return JspPageName.REDIRECT_TO_INDEX_PAGE;
	}
	
	private String getRedirectPageByGameKind(String gameKind){
		System.out.println("game kind"+gameKind);
		
		GameKind kindOfSelectedGame = GameKind.valueOf(gameKind.toUpperCase());
		
		if(kindOfSelectedGame == GameKind.FOOTBALL){
			return JspPageName.REDIRECT_TO_INDEX_WITH_FOOTBALL_GAME;
		}
		if(kindOfSelectedGame == GameKind.BASKETBALL){
			return JspPageName.REDIRECT_TO_INDEX_WITH_BASKETBALL_GAME;
		}
		if(kindOfSelectedGame == GameKind.HOCKEY){
			return JspPageName.REDIRECT_TO_INDEX_WITH_HOCKEY_GAME;
		}
		
		return JspPageName.REDIRECT_TO_INDEX_PAGE;
	}
}
