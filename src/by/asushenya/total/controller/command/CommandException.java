package by.asushenya.total.controller.command;

import by.asushenya.total.exception.ProjectException;

public class CommandException extends ProjectException {
	private static final long serialVersionUID = 1L;

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String message, Exception e) {
		super(message, e);
	}
}
