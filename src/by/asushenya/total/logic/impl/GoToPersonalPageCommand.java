package by.asushenya.total.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.util.Encryptor;

public class GoToPersonalPageCommand implements ICommand {

	@Override 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{

			
		return null;
	}
}
