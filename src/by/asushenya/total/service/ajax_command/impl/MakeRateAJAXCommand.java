package by.asushenya.total.service.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.sun.net.httpserver.HttpServer;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.AJAXCommandException;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.exception.ProjectException;
import by.asushenya.total.service.util.Validator;

public class MakeRateAJAXCommand{

	
		
	public void execute(){
		/*Director director = new Director();
		director.setBuilder(new RealRateBuilder(request,response));
		Rate rate = null;
		try{
			rate = director.buildRate();
		} catch(MakeRateAJAXException e){
			// log e
			return;
		}		

			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();	
			
			try {
				userDAO.makeRate(rate);
				returnSuccessToClient(response);
			} catch (DAOException e) {
				returnErrorToClient(response,
									e.getMessage());
				e.printStackTrace();
			}
			
	}	

	private static void returnErrorToClient(HttpServletResponse response,
											String errorMessage){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
		json.put(ResponseParameterName.ERROR_MSSAGE, errorMessage);
		System.out.println(json.toString());
	        PrintWriter pw = null;
			try {
				pw = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			} 
	        pw.print(json.toString());
	        pw.close();
	}
	
	private static void returnSuccessToClient(HttpServletResponse response){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);	
		System.out.println(json.toString());
	        PrintWriter pw = null;
			try {
				pw = response.getWriter();
			} catch (IOException e) {			
				e.printStackTrace();
			} 
			
	        pw.print(json.toString());
	        pw.close();
	}
	
	public class MakeRateAJAXException extends ProjectException{
		private static final long serialVersionUID = 1L;
		
		public MakeRateAJAXException(){
			super();
		}
		
		public MakeRateAJAXException(String message){
			super(message);
		}
		
		public MakeRateAJAXException(Exception e){
			super(e);
		}
		
		public MakeRateAJAXException(String message, Exception e){
			super (message, e);
		}
	}
	
	abstract class AbstractRateBuilder{
		Rate rate;
		void createRate(){
			rate = new Rate();
		}	
		
		abstract void buildUser() throws MakeRateAJAXException;
		abstract void buildGame() throws MakeRateAJAXException;
		abstract void buildMoney()throws MakeRateAJAXException ;
		abstract void buildChoice() throws MakeRateAJAXException;
		abstract void buildGameCoefficient() throws MakeRateAJAXException;		
		
		Rate getRate(){
			return rate;
		}
	}
	
	class RealRateBuilder extends AbstractRateBuilder{
		
		HttpServletRequest request;
		HttpServletResponse response;
		HttpSession session;
		
		public RealRateBuilder(HttpServletRequest request,HttpServletResponse response){
			this.request = request;
			this.response = response;
			this.session = request.getSession(true);
		}
		
		void buildUser() throws MakeRateAJAXException {
			User user = (User)session.getAttribute(RequestParameterName.USER);
			if(user != null){
				rate.setUser(user);	
			} else{
				returnErrorToClient(response,
									ResponseParameterName.EMPTY_USER);				
				throw new MakeRateAJAXException("user is no authorized");
			}
		}

		void buildGame() throws MakeRateAJAXException {
			Game game = new Game();
			int gameId;
		    gameId = Integer.parseInt(request.getParameter(RequestParameterName.GAME_ID));
				
			game.setId(gameId);
		
			rate.setGame(game);			
		}

		void buildMoney() throws MakeRateAJAXException {
			double money = 0;
			try{
				
				money =Double.parseDouble( request.getParameter(
										RequestParameterName.RATE_MONEY));
				
			} catch(NumberFormatException e){
				returnErrorToClient(response, 
						ResponseParameterName.RATE_NOT_A_NUMBER);
				throw new MakeRateAJAXException("rate not a number");
			}			
			
			if(money <= 0){
				
				returnErrorToClient(response, ResponseParameterName.LESS_THAN_ZERO);
				throw new MakeRateAJAXException("rate money less thaz zero");
			}
			
			
			if(money <= rate.getUser().getCash()){					
				rate.setMoney(money);
			}else{
				returnErrorToClient(response, 
						ResponseParameterName.NO_MONEY);
				throw new MakeRateAJAXException("user does not have enough money");
			}
		}

		void buildChoice() {
			String choice = request.getParameter(RequestParameterName.CHOICE);
			if(choice.equals("t1") ||
			   choice.equals("x")  ||
			   choice.equals("t2")){
				
				rate.setChoice(choice);
			} else{
				returnErrorToClient(response, ResponseParameterName.UNKNOWN_ERROR);
				throw new MakeRateAJAXException("unknown choice error");
			}
		}

		void buildGameCoefficient(){
			double coefficient;

				coefficient = Double.parseDouble(request.getParameter(
								RequestParameterName.RATE_COEFFICIENT));
				rate.setGameCoefficient(coefficient);			
		}		
	}
	
	class Director{
		AbstractRateBuilder builder;

		void setBuilder(AbstractRateBuilder builder){
			this.builder = builder;
		}
		
		Rate buildRate() throws MakeRateAJAXException{
			builder.createRate();
			builder.buildUser();
			builder.buildGame();
			builder.buildGameCoefficient();
			builder.buildChoice();
			builder.buildMoney();
			Rate rate = builder.getRate();
			return rate;
		}*/
	}
}
