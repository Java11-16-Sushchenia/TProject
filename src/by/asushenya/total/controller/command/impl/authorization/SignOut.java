package by.asushenya.total.controller.command.impl.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.SessionParameterName;

public class SignOut implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		request.getSession(true).removeAttribute(SessionParameterName.SESSION_USER);

		return JspPageName.REDIRECT_TO_INDEX_PAGE;
	}

}
