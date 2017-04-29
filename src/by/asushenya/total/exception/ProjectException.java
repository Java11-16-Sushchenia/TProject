package by.asushenya.total.exception;

/**
 * Class {@link ProjectException} is the root of the exception classes
 * hierarchy. Every exception class has {@link ProjectException} as a superclass
 * 
 * @author Artyom Suschenya
 *
 */
public class ProjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProjectException() {
		super();
	}

	public ProjectException(String message) {
		super(message);
	}

	public ProjectException(Exception e) {
		super(e);
	}

	public ProjectException(String message, Exception e) {
		super(message, e);
	}

}
