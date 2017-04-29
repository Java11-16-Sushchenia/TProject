package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;

/**
 * This command extract information from request about required language, and
 * change language according to extracted information.
 * 
 * @author Artyom Sushenya
 *
 */
public class ChangeLocalizationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		request.getSession(true).setAttribute(SessionAttributeName.LOCAL,
				request.getParameter(SessionAttributeName.LOCAL));

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}
}