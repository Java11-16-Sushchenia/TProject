package by.asushenya.total.service;

import by.asushenya.total.bean.User;
import by.asushenya.total.service.exception.ServiceException;

public interface AuthorizationService {
	User singIn(String login, String password) throws ServiceException;
	String signOut() throws ServiceException;
}
