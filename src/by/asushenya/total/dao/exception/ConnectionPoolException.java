package by.asushenya.total.dao.exception;

/**
 * Exception of this type can throws at dao layer and signal about problems at
 * connection pool.
 * 
 * @author Artyom Suschenya
 *
 */
public class ConnectionPoolException extends DAOException {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}