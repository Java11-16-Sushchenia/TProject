package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.bean.User;
import by.asushenya.total.controller.SessionParameterName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.util.PersonalPagesHelper;

public class GetPersonalPageCommand implements ICommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		User user = (User) request.getSession(true).getAttribute(SessionParameterName.SESSION_USER);

		return PersonalPagesHelper.getInstance().getPersonalPage(user.getRole());
	}

}
