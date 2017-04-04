package by.asushenya.total.controller.ajax_controller.ajax_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;

public interface IAJAXCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException;
}
