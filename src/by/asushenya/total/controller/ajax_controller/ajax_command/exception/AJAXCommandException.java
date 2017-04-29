package by.asushenya.total.controller.ajax_controller.ajax_command.exception;

import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.exception.ProjectException;

/**
 * Exception of this type can throws at {@link IAJAXCommand}'s implementation
 * classes.
 * 
 * @author Artyom Suschenya
 *
 */
public class AJAXCommandException extends ProjectException {
	private static final long serialVersionUID = 1L;

	public AJAXCommandException() {
		super();
	}

	public AJAXCommandException(String message) {
		super(message);
	}

	public AJAXCommandException(Exception e) {
		super(e);
	}

	public AJAXCommandException(String message, Exception e) {
		super(message, e);
	}
}
