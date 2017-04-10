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

		System.out.println("Имя команды: " + commandName);

		ICommand command = CommandHelper.getInstance().getCommand(commandName);

		String page = null;

		try {
			page = command.execute(request, response);
		} catch (CommandException e) {
			log.error("command executing error", e);
			page = JspPageName.ERROR_PAGE;
		} catch (Exception e) {
			log.error("command executing error", e);
			page = JspPageName.ERROR_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		if (dispatcher != null) {
			dispatcher.forward(request, response);
		} else {
			log.info("requestDispatcher is null");
		}
	}
}
