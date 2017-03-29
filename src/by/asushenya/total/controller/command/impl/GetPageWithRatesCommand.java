package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.RatesPage;
import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class GetPageWithRatesCommand implements ICommand{

	private static final Logger log = Logger.getLogger(
										GetPageWithRatesCommand.class);
	
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  	throws CommandException {
		
		int page = 1;
		int recordsPerPage = 5;	
		User user = (User)request.getSession(true).getAttribute("user");
		String local = (String)request.getSession(true).getAttribute("local");
		
		RatesPage pageWithUsers = new RatesPage();

		if(request.getParameter(RequestParameterName.PAGE_NUMBER) != null){
			page = Integer.parseInt(request.getParameter(
						RequestParameterName.PAGE_NUMBER));
		}
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		
		try{			
			pageWithUsers = userService.getRatesPage(user, 
													 page, 	
													 recordsPerPage, 
													 local);
		} catch(ServiceException e){
			log.error("can't get page with rates",e);
		}

		request.setAttribute("rates", pageWithUsers.getRatesList());
		request.setAttribute("noOfPages", pageWithUsers.getNumberOfPages());
		request.setAttribute("currentPage", page);

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}
}
