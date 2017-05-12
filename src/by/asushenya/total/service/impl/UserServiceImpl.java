package by.asushenya.total.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.page.GamesPage;
import by.asushenya.total.bean.page.RatesPage;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.InvalidArgumentServiceException;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

/**
 * Implements {@link UserService} interface.
 * 
 * @author Artyom Sushenya
 *
 */
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public GamesPage getGamesPage(int page, int gamesPerPage, GameKind gameKind, String local) throws ServiceException {

		if (!Validator.validatePageNumber(page)) {
			log.info("invalid page number");
			throw new InvalidArgumentServiceException("invalid page number");
		}
		if (!Validator.validatePageNumber(gamesPerPage)) {
			log.info("invalid games per page number");
			throw new InvalidArgumentServiceException("invalid games per page number");
		}
		int noOfRecords = 0;

		List<Game> gamesList = null;

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		try {
			gamesList = userDAO.getGamesForPage((page - 1) * gamesPerPage, gamesPerPage, gameKind, local);

			noOfRecords = userDAO.getGamesRecordsByGameKindCount(gameKind);
		} catch (DAOException e) {
			log.error("can't get games page form dao", e);
			throw new ServiceException("Can't get games from dao", e);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / gamesPerPage);

		GamesPage gamesPage = new GamesPage();
		gamesPage.setGamesList(gamesList);
		gamesPage.setNumberOfPages(noOfPages);

		return gamesPage;
	}

	@Override
	public String makeRate(int gameId, User user, RateChoice choice, double rateCoefficient, double rateMoney)
			throws ServiceException {

		HashMap<String, Object> jsonInfo = new HashMap<String, Object>();
		if (!Validator.validateId(gameId)) {

			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}
		if (!Validator.validateId(user.getId())) {
			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}
		if (!Validator.validateСoefficient(rateCoefficient)) {

			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_СOEFFICIENT);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}
		if (!Validator.validateMoney(rateMoney)) {

			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_MONEY);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}

		if (user.getCash() < rateMoney) {

			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.NO_MONEY);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			return makeRateError.toString();
		}

		Rate rate = new Rate();
		Game game = new Game();

		game.setId(gameId);
		rate.setGame(game);

		rate.setUser(user);
		rate.setChoice(choice);
		rate.setGameCoefficient(rateCoefficient);
		rate.setMoney(rateMoney);

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		try {
			userDAO.makeRate(rate);
		} catch (DAOException e) {
			log.error("can't make rate: some DAO problems", e);
			throw new ServiceException("can't make rate: some DAO problems", e);
		}

		jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);
		double cashAfterRate = user.getCash() - rateMoney;
		String cashString = String.format("%.1f", cashAfterRate);
		jsonInfo.put(ResponseParameterName.USER_CASH, cashString);
		JSONObject makeRateError = new JSONObject(jsonInfo);
		return makeRateError.toString();
	}

	@Override
	public String registrationUser(String login, String email, String password) throws ServiceException {

		HashMap<String, Object> registrationInfo = new HashMap<String, Object>();

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		if (!Validator.validateLogin(login)) {
			registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

			registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_LOGIN);
			return new JSONObject(registrationInfo).toString();
		}
		if (!Validator.validateEmail(email)) {
			registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

			registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_EMAIL);
			return new JSONObject(registrationInfo).toString();
		}

		try {
			if (userDAO.findUserByLogin(login) != null) {
				registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

				registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.USER_EXISTS);
				return new JSONObject(registrationInfo).toString();
			}
		} catch (DAOException e) {
			log.error("can't find user by login", e);
			throw new ServiceException();
		}

		try {
			if (userDAO.findUserByEmail(email) != null) {
				registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

				registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.USER_EMAIL_EXISTS);
				return new JSONObject(registrationInfo).toString();
			}
		} catch (DAOException e) {
			log.error("can't find user by email", e);
			throw new ServiceException();
		}

		User user = new User();
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);

		try {
			userDAO.registeredNewUser(user);
		} catch (DAOException e) {
			log.error("can't register user", e);
			throw new ServiceException("can't register user", e);
		}

		registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.SUCCESS);

		return registrationInfo.toString();
	}

	@Override
	public RatesPage getRatesPage(User user, int page, int ratesPerPage, String local) throws ServiceException {
		int noOfRecords = 0;

		if (!Validator.validateId(user.getId())) {
			return null;
		}
		if (!Validator.validatePageNumber(page)) {
			return null;
		}
		if (!Validator.validatePageNumber(ratesPerPage)) {
			return null;
		}

		List<Rate> ratesList = null;

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		try {
			ratesList = userDAO.getRatesForPage(user, (page - 1) * ratesPerPage, ratesPerPage, local);

			noOfRecords = userDAO.getRatesRecordsCountOfUser(user);
		} catch (DAOException e) {
			log.error("can't get rates page form dao", e);
			throw new ServiceException("Can't get rates from dao", e);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / ratesPerPage);

		RatesPage ratesPage = new RatesPage();
		ratesPage.setRatesList(ratesList);
		ratesPage.setNumberOfPages(noOfPages);

		return ratesPage;
	}
}
