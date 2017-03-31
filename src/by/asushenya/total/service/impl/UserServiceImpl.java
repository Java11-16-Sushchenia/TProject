package by.asushenya.total.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.GamesPage;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.bean.util.RatesPage;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	/*
	 * public List<Rate> getAllUserRates(User user) throws ServiceException {
	 * 
	 * List<Rate> userRates = null;
	 * 
	 * DAOFactory daoFactory = DAOFactory.getInstance(); UserDAO userDAO =
	 * daoFactory.getUserDAO();
	 * 
	 * try { userRates = userDAO.getAllUserRates(user);
	 * 
	 * } catch (DAOException e) { log.error("can't get user rates form dao", e);
	 * throw new ServiceException("can't get user rates", e); }
	 * 
	 * return userRates; }
	 */

	public GamesPage getGamesPage(int page, int gamesPerPage, GameKind gameKind, String local) throws ServiceException {

		if(!Validator.validatePageNumber(page)){
			return null;
		}
		if(!Validator.validatePageNumber(gamesPerPage)){
			return null;
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

	public String makeRate(int gameId, User user, RateChoice choice, double rateCoefficient, double rateMoney)
			throws ServiceException {
		
		if(!Validator.validateId(gameId)){
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			return makeRateError.toString();
		}
		if(!Validator.validateId(user.getId())){
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			return makeRateError.toString();
		}
		if(!Validator.validateСoefficient(rateCoefficient)){
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_СOEFFICIENT);
			return makeRateError.toString();
		}
		if(!Validator.validateMoney(rateMoney)){
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_MONEY);
			return makeRateError.toString();
		}

		if (user.getCash() < rateMoney) {
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.NO_MONEY);
			return makeRateError.toString();
		}
		Rate rate;
		Director director = new Director();
		RealRateBuilder builder = new RealRateBuilder(gameId, user, choice, rateCoefficient, rateMoney);
		director.setBuilder(builder);
		rate = director.buildRate();

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		try {
			userDAO.makeRate(rate);
		} catch (DAOException e) {
			log.error("can't make rate: some DAO problems", e);
			throw new ServiceException("can't make rate: some DAO problems", e);
		}

		JSONObject makeRateError = new JSONObject();
		makeRateError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.OK);
		return makeRateError.toString();

	}

	abstract class AbstractRateBuilder {
		Rate rate;

		void createRate() {
			rate = new Rate();
		}

		abstract void buildUser();

		abstract void buildGame();

		abstract void buildMoney();

		abstract void buildChoice();

		abstract void buildGameCoefficient();

		Rate getRate() {
			return rate;
		}
	}

	class RealRateBuilder extends AbstractRateBuilder {
		int gameId;
		User user;
		RateChoice choice;
		double rateCoefficient;
		double rateMoney;

		public RealRateBuilder(int gameId, User user, RateChoice choice, double rateCoefficient, double rateMoney) {

			this.gameId = gameId;
			this.user = user;
			this.choice = choice;
			this.rateCoefficient = rateCoefficient;
			this.rateMoney = rateMoney;
		}

		void buildGame() {
			Game game = new Game();
			game.setId(gameId);
			this.rate.setGame(game);
		}

		void buildUser() {
			this.rate.setUser(user);
		}

		void buildChoice() {
			this.rate.setChoice(choice);
		}

		void buildMoney() {
			this.rate.setMoney(rateMoney);
		}

		void buildGameCoefficient() {
			this.rate.setGameCoefficient(rateCoefficient);
		}
	}

	class Director {
		AbstractRateBuilder builder;

		void setBuilder(AbstractRateBuilder builder) {
			this.builder = builder;
		}

		Rate buildRate() {
			builder.createRate();
			builder.buildUser();
			builder.buildGame();
			builder.buildGameCoefficient();
			builder.buildChoice();
			builder.buildMoney();
			Rate rate = builder.getRate();
			return rate;
		}
	}

	public String registrationUser(String login, String email, String password) throws ServiceException {

		JSONObject registrationInfo = new JSONObject();

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		if (!Validator.validateLogin(login)) {
			registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

			registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_LOGIN);
			return registrationInfo.toString();
		}
		if (!Validator.validateEmail(email)) {
			registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

			registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_EMAIL);
			return registrationInfo.toString();
		}
		/*if (!Validator.validatePassword(password)) {
			registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

			registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_PASSWORD);
			return registrationInfo.toString();
		}*/

		try {
			if (userDAO.findUserByLogin(login) != null) {
				registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

				registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.USER_EXISTS);
				return registrationInfo.toString();
			}
		} catch (DAOException e) {
			log.error("can't find user by login", e);
			throw new ServiceException();
		}

		try {
			if (userDAO.findUserByEmail(email) != null) {
				registrationInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.REGISTRATION_ERROR);

				registrationInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.USER_EMAIL_EXISTS);
				return registrationInfo.toString();
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
		
		if(!Validator.validateId(user.getId())){
			return null;
		}
		if(!Validator.validatePageNumber(page)){
			return null;
		}
		if(!Validator.validatePageNumber(ratesPerPage)){
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
