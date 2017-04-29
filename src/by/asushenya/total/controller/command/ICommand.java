package by.asushenya.total.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.Controller;

/**
 * If some command implements this interface, {@link Controller} can execute it.
 * 
 * @author Artyom Sushenya
 *
 */
public interface ICommand {
	/**
	 * Method which have all commands.
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return URL of page at {@link String}. At the end of service servlet
	 *         forward user to this URL.
	 * @throws CommandException
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
