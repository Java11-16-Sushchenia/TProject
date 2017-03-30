package by.asushenya.total.service.impl;

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

		UserServiceObject userServiceObject = new UserServiceObject();
		JSONObject jsonWithAuthorizationInfo = new JSONObject();

		if (!Validator.validateLogin(login)) {
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_LOGIN);
			userServiceObject.setJsonWithErrors(jsonWithAuthorizationInfo.toString());
			return userServiceObject;
		}

		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e) {
			log.error("error by getting user by login", e);
		}

		if (user == null) {
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.NOT_REGISTRED);
			userServiceObject.setJsonWithErrors(jsonWithAuthorizationInfo.toString());
			return userServiceObject;
		}

		String enteredPasswordHash = Encryptor.getMD5Hash(password);

		if (!user.getPassword().equals(enteredPasswordHash)) {
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.AUTHORIZATION_ERROR);
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_PASSWORD);
			userServiceObject.setJsonWithErrors(jsonWithAuthorizationInfo.toString());
			return userServiceObject;
		}

		userServiceObject.setUser(user);
		jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.SUCCESS);
		jsonWithAuthorizationInfo.put(ResponseParameterName.USER_ROLE, user.getRole().toString());
		userServiceObject.setJsonWithSuccess(jsonWithAuthorizationInfo.toString());
		return userServiceObject;
	}

}
