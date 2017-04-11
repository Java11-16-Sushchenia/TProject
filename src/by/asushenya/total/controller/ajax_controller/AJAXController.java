package by.asushenya.total.controller.ajax_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;

/**
 * Class that responds to ajax requests
 * 
 * @author Artyom Asyshenya
 *
 */

public class AJAXController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AJAXController.class);

	public AJAXController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);

		IAJAXCommand command = AJAXCommandHelper.getInstance().getCommand(commandName);

		try {
			command.execute(request, response);
		} catch (AJAXCommandException e) {
			log.error("can't execute ajax command", e);
		} catch (Exception e) {
			log.error("AJAXController exception", e);
		}
	}
}
