package by.asushenya.total.dao.exception;

import by.asushenya.total.exception.ProjectException;

/**
 * Exception of this type can throws at dao layer methods implementation.
 * 
 * @author Artyom Suschenya
 *
 */
public class DAOException extends ProjectException {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
