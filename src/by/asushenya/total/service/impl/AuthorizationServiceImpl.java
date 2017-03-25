package by.asushenya.total.service.impl;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserServiceObject;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.AuthorizationService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Encryptor;

public class AuthorizationServiceImpl implements AuthorizationService{
	
	private static final Logger log = Logger.getLogger(
											 AuthorizationServiceImpl.class);

	public UserServiceObject singIn(String login, String password) 
										throws ServiceException {
		
		User user = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		UserServiceObject userServiceObject = new UserServiceObject();
		JSONObject jsonWithAuthorizationInfo = new JSONObject();
		
		try {
			user = userDAO.findUserByLogin(login);					
		} catch (DAOException e) {					
			log.error("error by getting user by login",e);
		}
		
		if(user == null){
			
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, 
										  ResponseParameterName.AUTHORIZATION_ERROR);
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_MSSAGE, 
										  ResponseParameterName.NOT_REGISTRED);			
			userServiceObject.setJsonWithErrors(jsonWithAuthorizationInfo.toString());			
			return userServiceObject;
		}	
		
		String enteredPasswordHash = Encryptor.getMD5Hash(password);
		
		if(!user.getPassword().equals(enteredPasswordHash)){
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE, 
										  ResponseParameterName.AUTHORIZATION_ERROR);
			jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_MSSAGE, 
										  ResponseParameterName.INVALID_PASSWORD);			
			userServiceObject.setJsonWithErrors(jsonWithAuthorizationInfo.toString());
			return userServiceObject;	
		}		
		
		
		userServiceObject.setUser(user);
		jsonWithAuthorizationInfo.put(ResponseParameterName.ERROR_TYPE,
									  ResponseParameterName.SUCCESS);
		jsonWithAuthorizationInfo.put(ResponseParameterName.USER_ROLE,
				  					  user.getRole().toString());
		userServiceObject.setJsonWithSuccess(jsonWithAuthorizationInfo.toString());
		return userServiceObject;			
		
	}

	/*public class SignInUserServiceException extends ServiceException{
		private static final long serialVersionUID = 1L;
		
		public SignInUserServiceException(){
			super();
		}
		
		public SignInUserServiceException(String message){
			super(message);
		}
		
		public SignInUserServiceException(Exception e){
			super(e);
		}
		
		public SignInUserServiceException(String message, Exception e){
			super (message, e);
		}
	}
	
	private static String returnAuthorizationError(String errorMessage){
		
			JSONObject json = new JSONObject();
			json.put(ResponseParameterName.ERROR_TYPE,
											ResponseParameterName.SIGN_IN_ERROR);
			json.put(ResponseParameterName.ERROR_MSSAGE, errorMessage);

			return json.toString();
	}
	
	private static String returnSuccessToClient(String userRole){
		JSONObject json = new JSONObject();
		json.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);
		json.put(ResponseParameterName.USER_ROLE, userRole);
		
		return json.toString();
	}*/
	
	/*abstract class AbstractUserBuilder{
		User user;
		void createUser(){
			user = new User();
		}
		
		abstract void buildLogin() throws AuthorizeUserAJAXException;
		abstract void buildPassword() throws AuthorizeUserAJAXException;
		
		User getUser(){
			return user;
		}
	}
	
	class RealUserBuilder extends AbstractUserBuilder{
		
		String login;
		String password;
		
		public RealUserBuilder(String login,
							   String password){
			this.login = login;
			this.password = password;
		}

		void buildLogin() throws AuthorizeUserAJAXException {				
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO userDAO = daoFactory.getUserDAO();
				
				try {
					this.user = userDAO.findUserByLogin(login);					
				} catch (DAOException e) {					
					e.printStackTrace();
				}
				
				if(this.user == null){
					return returnAuthorizationError(ResponseParameterName.NOT_REGISTRED);
				}				
		}

	
		void buildPassword() throws AuthorizeUserAJAXException {
			String enteredPasswordHash = Encryptor.getMD5Hash(this.password);
			
			if(!this.user.getPassword().equals(enteredPasswordHash)){
				returnErrorToClient(response, 
									ResponseParameterName.INVALID_PASSWORD);
				
				throw new AuthorizeUserAJAXException("password is incorrect");			
			}
		}		
	}*/
	
}
