package by.asushenya.total.service.exception;

import by.asushenya.total.exception.ProjectException;

/**
 * Exception of this type can throws service methods implementation.
 * 
 * @author Artyom Suschenya
 *
 */
public class ServiceException extends ProjectException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
