package by.asushenya.total.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.util.Encryptor;
import by.asushenya.total.logic.util.PersonalPagesHelper;

public class AuthorizationUserCommand implements ICommand {

	@Override 
	public String execute(HttpServletRequest request) throws CommandException{
	
		User user = null;
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		if( login == null || login.isEmpty()){
			request.setAttribute("userAuthorizationError", "Введите логин");		 
			return JspPageName.INDEX_PAGE;
		}
		
		if(password == null || password.isEmpty()  ){
			request.setAttribute("userAuthorizationError", "Введите пароль");
			return JspPageName.INDEX_PAGE;
		}		
		
		try {
			 user = DAOFactory.getInstance().getUserDAO().findUserByLogin(login);//искать пользователя одновременно и по поролю тоже
		
		} catch (DAOException e) {
			//log.error("Authorization error",e);
		}
					
		if(user != null){
			
			String passwordHash = Encryptor.getMD5(password);
			
			if(user.getPassword().equals(passwordHash)){
				session.setAttribute("user", user);	    	
		    	
		    	return PersonalPagesHelper.getInstance().getPersonalPage(user.getRole());				
		    	
			} else{
				request.setAttribute("userAuthorizationError", "Неверный пароль");
				return JspPageName.INDEX_PAGE;
			}		    		   
		}else {
			request.setAttribute("userAuthorizationError", "Нет такого пользователя");
			
			return JspPageName.INDEX_PAGE;
		}
	}
}
