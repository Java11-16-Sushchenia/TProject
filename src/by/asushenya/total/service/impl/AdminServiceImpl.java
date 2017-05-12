package by.asushenya.total.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.page.UsersPage;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.InvalidArgumentServiceException;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

/**
 * Implements {@link AdminService} interface.
 * 
 * @author Artyom Sushenya
 *
 */
public class AdminServiceImpl implements AdminService {

	private static final Logger log = Logger.getLogger(AdminServiceImpl.class);

	public UsersPage getUsersPage(int page, int usersPerPage) throws ServiceException {

		if (!Validator.validatePageNumber(page)) {
			log.error("users page value is invalid");
			throw new InvalidArgumentServiceException("users page value is invalid");
		}
		if (!Validator.validatePageNumber(usersPerPage)) {
			log.error("invalid user count per page value");
			throw new InvalidArgumentServiceException("invalid user count per page value");
		}

		int noOfRecords = 0;

		List<User> usersList = null;

		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();

		try {
			usersList = adminDAO.getUsersForPage((page - 1) * usersPerPage, usersPerPage);

			noOfRecords = adminDAO.getUsersRecordsCount();
		} catch (DAOException e) {
			throw new ServiceException("Can't get games from dao", e);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / usersPerPage);

		UsersPage usersPage = new UsersPage();
		usersPage.setUsersList(usersList);
		usersPage.setNumberOfPages(noOfPages);

		return usersPage;
	}

	public List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();
		List<Team> listOfTeams = null;

		try {
			listOfTeams = adminDAO.getTeamsByGameKind(gameKind, local);

		} catch (DAOException e) {
			throw new ServiceException("can't get list of teams", e);
		}

		return listOfTeams;
	}

	public String addNewGame(GameKind gameKind, String firstTeamName, String secondTeamName, Timestamp gameDate,
			double k1, double kx, double k2, String local) throws ServiceException {

		HashMap<String, Object> jsonInfo = new HashMap<String, Object>();

		if (!Validator.validateTeam(firstTeamName)) {
			jsonInfo.put(ResponseParameterName.ADD_NEW_GAME_ERROR, ResponseParameterName.INVALID_TEAM_NAME);
			return new JSONObject(jsonInfo).toString();
		}
		if (!Validator.validateTeam(secondTeamName)) {
			jsonInfo.put(ResponseParameterName.ADD_NEW_GAME_ERROR, ResponseParameterName.INVALID_TEAM_NAME);
			return new JSONObject(jsonInfo).toString();
		}
		if (!Validator.validateСoefficient(k1)) {
			jsonInfo.put(ResponseParameterName.ADD_NEW_GAME_ERROR, ResponseParameterName.INVALID_СOEFFICIENT);
			return new JSONObject(jsonInfo).toString();
		}
		if (!Validator.validateСoefficient(kx)) {
			jsonInfo.put(ResponseParameterName.ADD_NEW_GAME_ERROR, ResponseParameterName.INVALID_СOEFFICIENT);
			return new JSONObject(jsonInfo).toString();
		}
		if (!Validator.validateСoefficient(k2)) {
			jsonInfo.put(ResponseParameterName.ADD_NEW_GAME_ERROR, ResponseParameterName.INVALID_СOEFFICIENT);
			return new JSONObject(jsonInfo).toString();
		}
		Game newGame = new Game();

		newGame.setGameKind(gameKind);
		newGame.setFirstTeam(firstTeamName);
		newGame.setSecondTeam(secondTeamName);
		newGame.setDate(gameDate);
		newGame.setK1(k1);
		newGame.setKx(kx);
		newGame.setK2(k2);

		if (local == null) {
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();

		try {
			adminDAO.addGame(newGame, local);
		} catch (DAOException e) {
			throw new ServiceException("can't add new game", e);
		}

		jsonInfo.put(ResponseParameterName.SUCCESS, ResponseParameterName.OK);

		return new JSONObject(jsonInfo).toString();
	}

	public String blockUser(int userId) throws ServiceException {

		HashMap<String, Object> jsonInfo = new HashMap<String, Object>();
		if (!Validator.validateId(userId)) {
			log.error("user invalid id");
			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.USER_BLOCKING_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}

		User blockingUser = new User();
		blockingUser.setId(userId);

		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();

		try {
			adminDAO.blockUser(blockingUser);
		} catch (DAOException e) {
			throw new ServiceException("can't block user", e);
		}

		jsonInfo.put(ResponseParameterName.SUCCESS, ResponseParameterName.OK);
		JSONObject makeRateError = new JSONObject(jsonInfo);
		log.info("user with id " + blockingUser.getId() + " blocked");
		return makeRateError.toString();
	}

	public String unblockUser(int userId) throws ServiceException {
		HashMap<String, Object> jsonInfo = new HashMap<String, Object>();
		if (!Validator.validateId(userId)) {
			log.error("user invalid id");
			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.USER_UNBLOCKING_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}

		User blockingUser = new User();
		blockingUser.setId(userId);

		DAOFactory daoFactory = DAOFactory.getInstance();
		AdminDAO adminDAO = daoFactory.getAdminDAO();

		try {
			adminDAO.unblockUser(blockingUser);
		} catch (DAOException e) {
			throw new ServiceException("can't unblock user", e);
		}

		jsonInfo.put(ResponseParameterName.SUCCESS, ResponseParameterName.OK);
		JSONObject makeRateError = new JSONObject(jsonInfo);

		log.info("user with id " + blockingUser.getId() + " unblocked");

		return makeRateError.toString();
	}

}
