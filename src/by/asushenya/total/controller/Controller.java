package by.asushenya.total.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;

/**
 * 
 * Accept requests from client, extract and execute commands, that implements {@link ICommand}
 *@author Artyom Suschenya
 */

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Controller.class);

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);

		ICommand command = CommandHelper.getInstance().getCommand(commandName);

		String page = null;

		try {
			page = command.execute(request, response);
		} catch (CommandException e) {		
			page = JspPageName.ERROR_PAGE;
		} catch (Exception e) {

			page = JspPageName.ERROR_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			log.info("can't go over to another page");
		}
	}
}
