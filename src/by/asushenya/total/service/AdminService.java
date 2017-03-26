package by.asushenya.total.service;

import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.service.exception.ServiceException;

public interface AdminService{
	//String getAllUsers() throws ServiceException;
	UsersPage getUsersPage(int page,
						   int usersPerPage)
								throws ServiceException;
}
