package by.asushenya.total.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class GetAllUsersCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		List<User> users = null;
		
		try {
			users = DAOFactory.getInstance().getAdminDAO().getAllUsers();
			request.setAttribute("users", users);
			System.out.println("users count"+ users.size());
		} catch (DAOException e) {
			//log.error("getting users exception",e);
			e.printStackTrace();
		}
		
		return "adminPage";
	}
	
}
