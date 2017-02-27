package by.asushenya.total.service.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;

@WebServlet("/AuthorizationUserServlet")
public class AuthorizationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(GetAllGamesServlet.class);

    public AuthorizationUserServlet() {
        super();
    	PropertyConfigurator.configure("D:\\learn\\Java\\EclipseWorkspase\\TProject\\WebContent\\WEB-INF\\properties\\log4j.properties");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = null;
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if( login == null || login.isEmpty()){
			request.setAttribute("userAuthorizationError", "Введите логин");
			request.getRequestDispatcher("index.jsp").forward(request, response);  
			return;
		}
		
		if(password == null || password.isEmpty()  ){
			request.setAttribute("userAuthorizationError", "Введите пароль");
			request.getRequestDispatcher("index.jsp").forward(request, response);  
			return;
		}		
		
		try {
			 user = DAOFactory.getInstance().getUserDAO().findUserByLogin(login);
		
		} catch (DAOException e) {
			log.error("Authorization error",e);
		}
		
			
		if(user != null){
			user.getPassword().equals(password);
			  request.setAttribute("user", user);			  
		}else {
			request.setAttribute("userAuthorizationError", "Нет такого пользователя");
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);  
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
