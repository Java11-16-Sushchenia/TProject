package by.asushenya.total.controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;

public class ChangeLocalizationCommand implements ICommand{

	
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 	
								  	throws CommandException {	
		
		//Locale newLocale = new Locale(request.getParameter("local"));
		
		request.getSession(true).setAttribute("local", 
												request.getParameter("local"));	
		
		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
