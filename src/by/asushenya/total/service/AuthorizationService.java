package by.asushenya.total.service;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.service.exception.ServiceException;

/**
 * Provide user authorization methods.
 * 
 * @author Artyom Sushenya
 *
 */
public interface AuthorizationService {
	/**
	 * 
	 * @param login
	 *            login of user
	 * @param password
	 *            password of user
	 * @return returns object which contains user from dao or authorization
	 *         error
	 * @throws ServiceException
	 *             may throw ServiceException
	 */
	UserServiceObject singIn(String login, String password) throws ServiceException;

	/**
	 * Read new data about user from data source.
	 * 
	 * @param user
	 *            that need to refresh
	 * @return user with refreshed data
	 * @throws ServiceException
	 */

	User refreshUserData(User user) throws ServiceException;
}
