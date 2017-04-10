package by.asushenya.total.service.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.Encryptor;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger log = Logger.getLogger(AuthorizationServiceImpl.class);

	public UserServiceObject singIn(String login, String password) throws ServiceException {
		User user = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		HashMap<String, Object> authInfo = new HashMap<String, Object>();

		UserServiceObject userServiceObject = new UserServiceObject();

		if (!Validator.validateLogin(login)) {
			authInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			authInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_LOGIN);
			JSONObject authorizationError = new JSONObject(authInfo);
			userServiceObject.setJsonWithErrors(authorizationError.toString());
			return userServiceObject;
		}

		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e) {
			log.error("error by getting user by login", e);
		}

		if (user == null) {
			authInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			authInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.NOT_REGISTRED);
			JSONObject authorizationError = new JSONObject(authInfo);
			userServiceObject.setJsonWithErrors(authorizationError.toString());
			return userServiceObject;
		}

		String enteredPasswordHash = Encryptor.getMD5Hash(password);

		if (!user.getPassword().equals(enteredPasswordHash)) {
			authInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			authInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_PASSWORD);
			JSONObject authorizationError = new JSONObject(authInfo);
			userServiceObject.setJsonWithErrors(authorizationError.toString());
			return userServiceObject;
		}
		if (user.getIsVisible() == 0) {
			authInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			authInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.USER_BLOCKED);
			JSONObject authorizationError = new JSONObject(authInfo);
			userServiceObject.setJsonWithErrors(authorizationError.toString());
			return userServiceObject;
		}

		userServiceObject.setUser(user);

		authInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.SUCCESS);
		authInfo.put(ResponseParameterName.USER_ROLE, user.getRole().toString());
		JSONObject authorizationError = new JSONObject(authInfo);
		userServiceObject.setJsonWithSuccess(authorizationError.toString());
		return userServiceObject;
	}

	@Override
	public User refreshUserData(User user) throws ServiceException {

		User refreshedUser = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		if (!Validator.validateLogin(user.getLogin())) {
			throw new ServiceException("invalid user login");
		}

		try {
			refreshedUser = userDAO.findUserByLogin(user.getLogin());
		} catch (DAOException e) {
			log.error("error by getting user by login", e);
		}

		if (refreshedUser == null) {
				throw new ServiceException("can't get user from dao");
		}

		if (!user.getPassword().equals(refreshedUser.getPassword())) {
			throw new ServiceException("passwords do not match");
		}

		return refreshedUser;
	}

}
