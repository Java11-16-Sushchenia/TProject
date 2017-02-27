package by.asushenya.total.exception;

public class ProjectException extends Exception{
	private static final long serialVersionUID = 1L;
	private Exception hiddenException;
	
	public ProjectException(){
		super();
	}
	
	public ProjectException(String message){
		super(message);
	}
	
	public ProjectException(Exception e){
		super(e);
	}
	
	public ProjectException(String message, Exception e){
		super (message, e);
		hiddenException = e;
	}
	
	public Exception getHiddenException(){
		return hiddenException;
	}
}
