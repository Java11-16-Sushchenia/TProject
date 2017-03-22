package by.asushenya.total.logic.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.dao.impl.UserDAOImpl;
import by.asushenya.total.exception.ProjectException;
import by.asushenya.total.logic.ajax_command.AJAXCommandException;
import by.asushenya.total.logic.ajax_command.IAJAXCommand;
import by.asushenya.total.logic.util.Encryptor;

public class RegistrateUserAJAXCommand implements IAJAXCommand{

	private static final Logger log = Logger.getLogger(RegistrateUserAJAXCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {
		System.out.println("start registration");
		Director director = new Director();
		director.setBuilder(new RealUserBuilder(request, response));
		User user = null;
		
		try{
			user = director.buildUser();
		} catch(RegistrateUserAJAXException e){
			log.error("can't authorize user",e);
			return;
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try{
			userDAO.registeredNewUser(user);
		} catch(DAOException e){
			log.error("can't registrate user",e);
			throw new AJAXCommandException("can't registrate user",e);
		}	
		
		returnSuccessToClient(response);
		System.out.println("user us registred");
	}
	
	private static void returnErrorToClient(HttpServletResponse response,
			String errorMessage){
		
			JSONObject json = new JSONObject();
			json.put(ResponseParameterName.ERROR_TYPE,
											ResponseParameterName.AUTHORIZATION_ERROR);
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
	
	public class RegistrateUserAJAXException extends ProjectException{
		private static final long serialVersionUID = 1L;
		
		public RegistrateUserAJAXException(){
			super();
		}
		
		public RegistrateUserAJAXException(String message){
			super(message);
		}
		
		public RegistrateUserAJAXException(Exception e){
			super(e);
		}
		
		public RegistrateUserAJAXException(String message, Exception e){
			super (message, e);
		}
	}
	
	abstract class AbstractUserBuilder{
		User user;
		void createUser(){
			user = new User();
		}
		
		abstract void buildLogin() throws RegistrateUserAJAXException;
		abstract void buildEmail() throws RegistrateUserAJAXException;
		abstract void buildPassword() throws RegistrateUserAJAXException;
		
		User getUser(){
			return user;
		}
	}
	
	class RealUserBuilder extends AbstractUserBuilder{
		
		HttpServletRequest request;
		HttpServletResponse response;
		HttpSession session;
		
		public RealUserBuilder(HttpServletRequest request,
							   HttpServletResponse response){
			this.request = request;
			this.response = response;
		}

		void buildLogin() throws RegistrateUserAJAXException {	
			String login = request.getParameter(RequestParameterName.LOGIN);
			User testUser = null;
			
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			
			try {
				testUser = userDAO.findUserByLogin(login);					
			} catch (DAOException e) {					
				log.error("can't find user by login",e);
				throw new RegistrateUserAJAXException("cant registrate new user",e);
			}
			
			if(testUser != null){
				returnErrorToClient(response,
						ResponseParameterName.USER_EXISTS);
				throw new RegistrateUserAJAXException("user is always exists");
			}	
			
			user.setLogin(login);
		}
		
		void buildEmail() throws RegistrateUserAJAXException{
			String newUserEmail = request.getParameter(
							RequestParameterName.USER_EMAIL);
			User testUser = null;
			
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			
			try {
				testUser = userDAO.findUserByEmail(newUserEmail);				
			} catch (DAOException e) {					
				log.error("can't find user by email",e);
				throw new RegistrateUserAJAXException("cant registrate new user",e);
			}
			
			if(testUser != null){
				returnErrorToClient(response,
						ResponseParameterName.USER_EXISTS);
				throw new RegistrateUserAJAXException("user is always exists");
			}	
			
			user.setEmail(newUserEmail);
		}

	
		void buildPassword() throws RegistrateUserAJAXException {
			String newUserPasswordHash = Encryptor.getMD5Hash(
					request.getParameter(RequestParameterName.PASSWORD));
			
			user.setPassword(newUserPasswordHash);
		}		
	}
	
	class Director{
		AbstractUserBuilder builder;
		
		void setBuilder(AbstractUserBuilder builder){
			this.builder = builder;
		}
		
		User buildUser() throws RegistrateUserAJAXException{
			builder.createUser();
			builder.buildLogin();
			builder.buildEmail();
			builder.buildPassword();
			User user = builder.getUser();
			return user;
		}
	}

}
