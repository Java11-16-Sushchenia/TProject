package by.asushenya.total.service.factory;

import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.InitializationSourceService;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.impl.AdminServiceImpl;
import by.asushenya.total.service.impl.AuthorizationServiceImpl;
import by.asushenya.total.service.impl.BookMakerServiceImpl;
import by.asushenya.total.service.impl.InitializationSourceServiceImpl;
import by.asushenya.total.service.impl.UserServiceImpl;

/**
 * 
 * Provides access to instances of service objects. Contain static field through
 * you can access to service interfaces.
 * 
 * @author Artyom Sushenya
 *
 */
public class ServiceFactory {
	private static final ServiceFactory serviceFactory = new ServiceFactory();

	private ServiceFactory() {
	}

	private UserService userService = new UserServiceImpl();
	private BookMakerService bookMakerService = new BookMakerServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	private AuthorizationService authorizationService = new AuthorizationServiceImpl();
	private InitializationSourceService initializationService = new InitializationSourceServiceImpl();

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public BookMakerService getBookMakerService() {
		return bookMakerService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public InitializationSourceService getInitializationSourceService() {
		return initializationService;
	}

	public static ServiceFactory getInstance() {
		return serviceFactory;
	}
}
