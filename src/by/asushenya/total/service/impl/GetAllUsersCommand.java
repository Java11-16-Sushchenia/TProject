package by.asushenya.total.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.CommandException;
import by.asushenya.total.service.ICommand;

public class GetAllUsersCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		/*Необходим аналон пагинации, как игр*/
		/*List<User> users = null;
		
		try {
			users = DAOFactory.getInstance().getAdminDAO().getAllUsers();
			request.setAttribute("users", users);
			System.out.println("users count"+ users.size());
		} catch (DAOException e) {
			//log.error("getting users exception",e);
			e.printStackTrace();
		}*/
		
		return "adminPage";
	}
	
}
