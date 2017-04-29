package by.asushenya.total.controller.ajax_controller.ajax_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
/**
 * If some command implements this interface, that {@link AJAXController} can execute this command
 * @author Artyom Asushenya 
 *
 */
public interface IAJAXCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException;
}
