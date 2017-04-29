package by.asushenya.total.service.exception;

/**
 * Exception of this type can throws if service layer receive invalid arguments.
 * 
 * @author Artyom Suschenya
 *
 */
public class InvalidArgumentServiceException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public InvalidArgumentServiceException() {
		super();
	}

	public InvalidArgumentServiceException(String message) {
		super(message);
	}

	public InvalidArgumentServiceException(Exception e) {
		super(e);
	}

	public InvalidArgumentServiceException(String message, Exception e) {
		super(message, e);
	}
}
