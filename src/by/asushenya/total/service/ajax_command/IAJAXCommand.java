package by.asushenya.total.service.ajax_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface IAJAXCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException;
}
