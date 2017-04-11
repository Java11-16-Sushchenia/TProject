package by.asushenya.total.dao.factory;

import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.InitializationSourceDAO;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.impl.AdminDAOImpl;
import by.asushenya.total.dao.impl.BookMakerDAOImpl;
import by.asushenya.total.dao.impl.InitializationSourceDAOImpl;
import by.asushenya.total.dao.impl.UserDAOImpl;


/**
 * 
 * Represents instance of data source
 *
 */
public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();

	private DAOFactory() {
	}
	private UserDAO userDAO = new UserDAOImpl();
	private BookMakerDAO bookMakerDAO = new BookMakerDAOImpl();
	private AdminDAO adminDAO = new AdminDAOImpl();
	private InitializationSourceDAO initializationSourceDAO = new InitializationSourceDAOImpl();

	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BookMakerDAO getBookMakerDAO() {
		return bookMakerDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public InitializationSourceDAO getInitializationSourceDAO() {
		return initializationSourceDAO;
	}

	public static DAOFactory getInstance() {
		return daoFactory;
	}
}
