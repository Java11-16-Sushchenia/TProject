package by.asushenya.total.logic.impl;

import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.CommandException;

import javax.servlet.http.HttpServletRequest;

public class LocalizationCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		request.getSession(true).setAttribute("local", 
												request.getParameter("local"));	
		
		return request.getParameter("go_to_page");//request.getRequestURI();
	}

}
