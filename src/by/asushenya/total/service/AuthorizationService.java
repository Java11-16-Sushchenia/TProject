package by.asushenya.total.service;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.service.exception.ServiceException;

public interface AuthorizationService {
	UserServiceObject singIn(String login, String password) throws ServiceException;

	User refreshUserData(User user) throws ServiceException;
}
