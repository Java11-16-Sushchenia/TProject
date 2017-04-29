package by.asushenya.total.controller.command.impl.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.controller.JspPageName;

/**
 * If user want to sign out from system this command is called and destroy user
 * session.
 * 
 * @author Artyom Sushenya
 *
 */
public class SignOut implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		request.getSession(true).invalidate();

		return JspPageName.REDIRECT_TO_INDEX_PAGE;
	}

}
