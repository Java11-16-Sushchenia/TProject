package by.asushenya.total.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.User;
import by.asushenya.total.service.CommandException;
import by.asushenya.total.service.ICommand;
import by.asushenya.total.service.util.PersonalPagesHelper;

public class GetPersonalPageCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		User user =(User) request.getSession(true).getAttribute("user");
		
		return PersonalPagesHelper.getInstance().getPersonalPage(user.getRole());
	}

}
