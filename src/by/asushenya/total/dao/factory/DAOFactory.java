package by.asushenya.total.dao.factory;

import org.apache.log4j.Logger;

import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.impl.AdminDAOImpl;
import by.asushenya.total.dao.impl.BookMakerDAOImpl;
import by.asushenya.total.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final Logger log = Logger.getLogger(DAOFactory.class);
	
	private static final DAOFactory daoFactory = new DAOFactory();
		
	static{
		 try {
			
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			
			log.error(e);
		}	  
	}
	
	private DAOFactory(){}		
	
	private UserDAO userDAO = new UserDAOImpl();
	private BookMakerDAO bookMakerDAO = new BookMakerDAOImpl();
	private AdminDAO adminDAO = new AdminDAOImpl();
	
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
	public BookMakerDAO getBookMakerDAO(){
		return bookMakerDAO;
	}
	public AdminDAO getAdminDAO(){
		return adminDAO;
	}
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}
}
