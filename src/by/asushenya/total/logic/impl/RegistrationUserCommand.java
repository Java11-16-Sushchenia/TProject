package by.asushenya.total.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.util.Encryptor;

public class RegistrationUserCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
			return null;
		/*User user = new User();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if( login == null || login.isEmpty()){
			System.out.println("loging is empty");
			request.setAttribute("userRegistrationError", "emptylogin");		 
			return JspPageName.REGISTRATION_PAGE;
		}		
		if(email == null || email.isEmpty()  ){
			request.setAttribute("userRegistrationError", "emptyemail");
			return JspPageName.REGISTRATION_PAGE;
		}	
		if(password == null || password.isEmpty()  ){
			request.setAttribute("userRegistrationError", "emptypassword");
			return JspPageName.REGISTRATION_PAGE;
		}		
		
		User testUser =  null;
		try {
			testUser = DAOFactory.getInstance().getUserDAO().findUserByLogin(login);
		} catch (DAOException e) {
			//log
			//e.printStackTrace();
		}
		
		if(testUser != null){
			request.setAttribute("userRegistrationError", "loginalreadyexists");	
			return JspPageName.REGISTRATION_PAGE;
		}		
		
		try {
			testUser = DAOFactory.getInstance().getUserDAO().findUserByEmail(email);
		} catch (DAOException e) {
			// log.error(e);
			//e.printStackTrace();
		}
		if(testUser != null){
			request.setAttribute("userRegistrationError", "emailalreadyexists");	
			return JspPageName.REGISTRATION_PAGE;
		}

		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(Encryptor.getMD5Hash(password));
		
		
		
		try {
			DAOFactory.getInstance().getUserDAO().registeredNewUser(user);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
		System.out.println("registration successful");		
		
		return JspPageName.REDIRECT_TO_INDEX_PAGE;*/
		
		
	}
}
