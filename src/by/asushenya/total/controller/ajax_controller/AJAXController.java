package by.asushenya.total.controller.ajax_controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.CommandHelper;
import by.asushenya.total.logic.ICommand;
import by.asushenya.total.logic.ajax_command.AJAXCommandException;
import by.asushenya.total.logic.ajax_command.AJAXCommandHelper;
import by.asushenya.total.logic.ajax_command.IAJAXCommand;

@WebServlet("/AJAXController")
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
