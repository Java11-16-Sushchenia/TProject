package by.asushenya.total.service.impl;

import by.asushenya.total.service.ICommand;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.service.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		request.getSession(true).setAttribute("local", 
												request.getParameter("local"));	
		
		return request.getParameter(RequestParameterName.GO_TO_PAGE);//request.getRequestURI();
	}

}
