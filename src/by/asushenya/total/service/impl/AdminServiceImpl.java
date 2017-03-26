package by.asushenya.total.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService{

	private static final Logger log = Logger.getLogger(
										AdminServiceImpl.class);
	@Override
	public UsersPage getUsersPage(int page,
								  int usersPerPage) 
										  throws ServiceException {
		
		int noOfRecords = 0;
		
		List<User> usersList = null;		

		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();
		
		try{
			usersList = adminDAO.getUsersForPage(page, usersPerPage);

			 noOfRecords = adminDAO.getUsersRecordsCount();
		} catch(DAOException e){
			log.error("can't get games page form dao",e);
			throw new ServiceException("Can't get games from dao",e);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / usersPerPage);
		
		UsersPage usersPage = new UsersPage();
		usersPage.setUsersList(usersList);
		usersPage.setNumberOfPages(noOfPages);
		
		return usersPage;
	}

}
