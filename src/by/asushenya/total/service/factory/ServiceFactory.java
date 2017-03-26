package by.asushenya.total.service.factory;

import by.asushenya.total.dao.impl.AdminDAOImpl;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.impl.AdminServiceImpl;
import by.asushenya.total.service.impl.AuthorizationServiceImpl;
import by.asushenya.total.service.impl.BookMakerServiceImpl;
import by.asushenya.total.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory serviceFactory = new ServiceFactory();
	
	private ServiceFactory(){}
	
	private UserService userService = new UserServiceImpl();
	private BookMakerService bookMakerService = new BookMakerServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	private AuthorizationService authorizationService = new AuthorizationServiceImpl();
	
	public AuthorizationService getAuthorizationService(){
		return authorizationService;
	}	
	public UserService getUserService(){
		return userService;
	}	
	public BookMakerService getBookMakerService(){
		return bookMakerService;
	}	
	public AdminService getAdminService(){
		return adminService;
	}	
	public static ServiceFactory getInstance(){
		return serviceFactory;
	}
}
