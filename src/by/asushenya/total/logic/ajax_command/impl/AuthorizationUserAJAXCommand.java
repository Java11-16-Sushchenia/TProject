package by.asushenya.total.logic.ajax_command.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.exception.ProjectException;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.ajax_command.AJAXCommandException;
import by.asushenya.total.logic.ajax_command.IAJAXCommand;
import by.asushenya.total.logic.util.Encryptor;
import by.asushenya.total.logic.util.PersonalPagesHelper;

public class AuthorizationUserAJAXCommand implements IAJAXCommand {

	@Override 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException{
	
		System.out.println("AJAXAuthorizeCommand"+request.getParameter("login")+request.getParameter("password"));
		
		Director director = new Director();
		director.setBuilder(new RealUserBuilder(request, response));
		User user = null;
		
		try{
			user = director.buildUser();
		} catch(AuthorizeUserAJAXException e){
			//e.printStackTrace();
			System.out.println("user authorization error"+e.getMessage());
			return;
		}
		
		request.getSession(true).setAttribute("user", user);
		returnSuccessToClient(response,user.getRole().toString());

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

	private static void returnSuccessToClient(HttpServletResponse response,String userRole){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);
		json.put(ResponseParameterName.USER_ROLE, userRole);
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
	
	public class AuthorizeUserAJAXException extends ProjectException{
		private static final long serialVersionUID = 1L;
		
		public AuthorizeUserAJAXException(){
			super();
		}
		
		public AuthorizeUserAJAXException(String message){
			super(message);
		}
		
		public AuthorizeUserAJAXException(Exception e){
			super(e);
		}
		
		public AuthorizeUserAJAXException(String message, Exception e){
			super (message, e);
		}
	}
	
	abstract class AbstractUserBuilder{
		User user;
		void createUser(){
			user = new User();
		}
		
		abstract void buildLogin() throws AuthorizeUserAJAXException;
		abstract void buildPassword() throws AuthorizeUserAJAXException;
		
		User getUser(){
			return user;
		}
	}
	
	class RealUserBuilder extends AbstractUserBuilder{
		
		HttpServletRequest request;
		HttpServletResponse response;
		HttpSession session;
		
		public RealUserBuilder(HttpServletRequest request,HttpServletResponse response){
			this.request = request;
			this.response = response;
		}

		void buildLogin() throws AuthorizeUserAJAXException {	
			String login = request.getParameter(RequestParameterName.LOGIN);
			
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();
				
				try {
					this.user = userDAO.findUserByLogin(login);					
				} catch (DAOException e) {					
					e.printStackTrace();
				}
				
				if(this.user == null){
					returnErrorToClient(response,
							ResponseParameterName.NOT_REGISTRED);
					throw new AuthorizeUserAJAXException("user is not registered");
				}				
		}

	
		void buildPassword() throws AuthorizeUserAJAXException {
			String enteredPasswordHash = Encryptor.getMD5Hash(
					request.getParameter(RequestParameterName.PASSWORD));
			
			if(!this.user.getPassword().equals(enteredPasswordHash)){
				returnErrorToClient(response, ResponseParameterName.INVALID_PASSWORD);
				
				throw new AuthorizeUserAJAXException("password is incorrect");			}
		}		
	}
	
	class Director{
		AbstractUserBuilder builder;
		
		void setBuilder(AbstractUserBuilder builder){
			this.builder = builder;
		}
		
		User buildUser() throws AuthorizeUserAJAXException{
			builder.createUser();
			builder.buildLogin();
			builder.buildPassword();
			User user = builder.getUser();
			return user;
		}
	}
}
