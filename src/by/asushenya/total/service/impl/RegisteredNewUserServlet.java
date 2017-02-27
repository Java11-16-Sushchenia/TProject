package by.asushenya.total.service.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.bean.User;

@WebServlet("/RegisteredNewUserServlet")
public class RegisteredNewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisteredNewUserServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(login == null) return;
		if(login.isEmpty()) return;
		
		if(email == null) return;
		if(email.isEmpty()) return;
		
		if(password == null) return;
		if(password.isEmpty()) return;
		
		User user = new User();
		
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);
		
		try {
			DAOFactory.getInstance().getUserDAO().registeredNewUser(user);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
		System.out.println("registration successful");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
