package by.asushenya.total.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.CommandHelper;
import by.asushenya.total.logic.ICommand;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		
		System.out.println("Имя команды: "+commandName);
		
		ICommand command = CommandHelper.getInstance().getCommand(commandName);
		System.out.println("имя команды"+command.toString());
		
		String page = null;
		
		try{
			page = command.execute(request,response);
		} catch(CommandException e){
			page = JspPageName.ERROR_PAGE;
			e.printStackTrace();
		} catch(Exception e){
			page = JspPageName.ERROR_PAGE;
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);

		if(dispatcher != null){
			dispatcher.forward(request, response);
		} else{
			
		}		
	}
	private void errorMessageDireclyFromresponse(HttpServletResponse response) 
														throws IOException{
		response.setContentType("text/html");
		response.getWriter().println("ERROR");
	}
}
