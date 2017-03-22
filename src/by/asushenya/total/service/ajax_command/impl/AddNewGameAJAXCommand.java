package by.asushenya.total.service.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.exception.ProjectException;
import by.asushenya.total.service.ajax_command.AJAXCommandException;
import by.asushenya.total.service.ajax_command.IAJAXCommand;

public class AddNewGameAJAXCommand implements IAJAXCommand{


	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {
		
		String local = (String)request.getSession(true).getAttribute("local");
		BuildGameDirector director = new BuildGameDirector();
		director.setBuilder(new RealGameBuilder(request, response));
		Game game = null;
		try{
			game = director.buildGame();
		} catch(AddNewGameException e){
			//log e
			return ;
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();
		
		try{
			adminDAO.addGame(game, local);
		} catch(DAOException e){
			// log error
		}
		
		returnSuccessToClient(response);
	}
	
	private static void returnSuccessToClient(HttpServletResponse response){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.SUCCESS, ResponseParameterName.OK);	
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
	
	public class AddNewGameException extends ProjectException{
		private static final long serialVersionUID = 1L;
		
		public AddNewGameException(){
			super();
		}
		
		public AddNewGameException(String message){
			super(message);
		}
		
		public AddNewGameException(Exception e){
			super(e);
		}
		
		public AddNewGameException(String message, Exception e){
			super (message, e);
		}
	}
	
	abstract class AbstractGameBuilder{
		Game game;
		
		void createGame(){
			game = new Game();
		}
		
		abstract void buildGameKind() throws AddNewGameException;
		abstract void buildFirstTeam() throws AddNewGameException;
		abstract void buildSeconTeam() throws AddNewGameException;
		abstract void buildGameDate() throws AddNewGameException;
		abstract void buildK1() throws AddNewGameException;
		abstract void buildKx() throws AddNewGameException;
		abstract void buildK2() throws AddNewGameException;
		
		Game getGame(){
			return game;
		}
	}
	
	class RealGameBuilder extends AbstractGameBuilder{
		HttpServletRequest request;
		HttpServletResponse response;
		
		public RealGameBuilder(HttpServletRequest request, HttpServletResponse response){
			this.request = request;
			this.response = response;
		}


		void buildGameKind() throws AddNewGameException {
			GameKind addedGameKind = GameKind.valueOf(
					request.getParameter(RequestParameterName.GAME_KIND).
															 toUpperCase());
			
			game.setGameKind(addedGameKind);			
		}


		void buildFirstTeam() throws AddNewGameException {
			String firstTeam = request.getParameter(
					RequestParameterName.FIRST_TEAM);
			game.setFirstTeam(firstTeam);
			
		}

		@Override
		void buildSeconTeam() throws AddNewGameException {
			String secondTeam = request.getParameter(
					RequestParameterName.SECOND_TEAM);		
		}

		@Override
		void buildGameDate() throws AddNewGameException {
			Timestamp gameDate = Timestamp.valueOf(
					request.getParameter(
							RequestParameterName.GAME_DATE));
			game.setDate(gameDate);
			
		}

		@Override
		void buildK1() throws AddNewGameException {
			double k1 =Double.parseDouble(
					request.getParameter(RequestParameterName.K1));			
			game.setK1(k1);			
		}

		@Override
		void buildKx() throws AddNewGameException {
			double kx =Double.parseDouble(
					request.getParameter(RequestParameterName.KX));			
			game.setKx(kx);			
		}

		@Override
		void buildK2() throws AddNewGameException {
			double k2 =Double.parseDouble(
					request.getParameter(RequestParameterName.K2));			
			game.setK2(k2);			
		}
	}
	
	class BuildGameDirector{
		AbstractGameBuilder builder;
		
		void setBuilder(AbstractGameBuilder builder){
			this.builder = builder;
		}
		
		Game buildGame() throws AddNewGameException{
			builder.createGame();
			builder.buildGameKind();
			builder.buildFirstTeam();
			builder.buildSeconTeam();
			builder.buildGameDate();
			builder.buildK1();
			builder.buildKx();
			builder.buildK2();
			Game game = builder.getGame();
			return game;
		}
	}

}
