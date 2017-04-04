package by.asushenya.total.controller.ajax_controller;

import by.asushenya.total.exception.ProjectException;

public class AJAXCommandException extends ProjectException {
	private static final long serialVersionUID = 1L;

	public AJAXCommandException(String message) {
		super(message);
	}

	public AJAXCommandException(String message, Exception e) {
		super(message, e);
	}
}
