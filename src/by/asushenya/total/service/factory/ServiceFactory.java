package by.asushenya.total.service.factory;

import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.impl.AuthorizationServiceImpl;
import by.asushenya.total.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory serviceFactory = new ServiceFactory();
	
	private ServiceFactory(){}
	
	private UserService userService = new UserServiceImpl();
	private BookMakerService bookMakerService = null;
	private AdminService adminService = null;
	private AuthorizationService authorizationService = new AuthorizationServiceImpl();
	
	public AuthorizationService getAuthorizationService(){
		return authorizationService;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public static ServiceFactory getInstance(){
		return serviceFactory;
	}
}
