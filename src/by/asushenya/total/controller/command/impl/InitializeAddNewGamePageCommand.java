package by.asushenya.total.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspPage;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.JspPageName;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.GetTeamsByGameKindAJAXCommand;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class InitializeAddNewGamePageCommand implements ICommand{
	private static final Logger log = Logger.getLogger(
										InitializeAddNewGamePageCommand.class);

	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws CommandException {
		
		List<Team> listOfTeams = null;
		String local = (String)request.getSession(true).getAttribute("local");
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		
		try{			
			listOfTeams = adminService.getTeamsByGameKind(GameKind.FOOTBALL, local);
		} catch(ServiceException e){
			log.error("can't get football teams",e);
		}
		
		request.setAttribute("teams", listOfTeams);
		
		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
