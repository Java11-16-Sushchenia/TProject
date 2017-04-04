package by.asushenya.total.controller.ajax_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;

public class AJAXController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AJAXController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
												throws ServletException, IOException {

		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);

		IAJAXCommand command = AJAXCommandHelper.getInstance().getCommand(commandName);

		try{
			 command.execute(request,response);
		} catch(AJAXCommandException e){
			
			e.printStackTrace();
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
	}

}
