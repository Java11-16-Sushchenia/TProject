package by.asushenya.total.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.service.CommandException;
import by.asushenya.total.service.ICommand;

public class LogOutCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		request.getSession(true).removeAttribute("user");
		
		return JspPageName.REDIRECT_TO_INDEX_PAGE;
	}

}
