package by.asushenya.total.controller.ajax_controller.ajax_command.exception;

import by.asushenya.total.exception.ProjectException;

public class AJAXCommandException extends ProjectException{
	private static final long serialVersionUID = 1L;
	
	public AJAXCommandException(){
		super();
	}
	
	public AJAXCommandException(String message){
		super(message);
	}
	
	public AJAXCommandException(Exception e){
		super(e);
	}
	
	public AJAXCommandException(String message, Exception e){
		super (message, e);
	}
}
