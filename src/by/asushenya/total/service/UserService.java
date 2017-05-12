package by.asushenya.total.service;

import by.asushenya.total.bean.User;
import by.asushenya.total.bean.page.GamesPage;
import by.asushenya.total.bean.page.RatesPage;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.service.exception.ServiceException;

/**
 * Contains all methods which {@link User} can use.
 * 
 * @author Artyom Sushenya
 *
 */
public interface UserService {
	/**
	 * Registrate new {@link User} at system.
	 * 
	 * @param login
	 *            New {@link User} login.
	 * @param email
	 *            New {@link User} email.
	 * @param password
	 *            New {@link User} password.
	 * @return Success or error message at json format.
	 * @throws ServiceException
	 */
	String registrationUser(String login, String email, String password) throws ServiceException;

	/**
	 * Return {@link GamesPage} object which contain List of games or error
	 * message.
	 * 
	 * @param page
	 *            Number of necessary by user page.
	 * @param recordsPerPage
	 *            Number of {@link Game}s at one page.
	 * @param gameKind
	 *            {@link Game}s selectionn parameter.
	 * @param local
	 *            Current user language parameters
	 * @return {@link GamesPage} object with List of {@link Game}s of with error
	 *         message in json format.
	 * @throws ServiceException
	 */
	GamesPage getGamesPage(int page, int recordsPerPage, GameKind gameKind, String local) throws ServiceException;

	/**
	 * Return {@link RatesPage} object which contain List of rates or error
	 * message.
	 * 
	 * @param user
	 *            Rates of this {@link User} contain List of rates.
	 * @param page
	 *            Number of necessary user rates page.
	 * @param recordsPerPage
	 *            Number of {@link Rate}s records at one page.
	 * @param local
	 *            Current user language parameters
	 * @return {@link RatesPage} object with List of {@link Rate}s of with error
	 *         message in json format.
	 * @throws ServiceException
	 */
	RatesPage getRatesPage(User user, int page, int recordsPerPage, String local) throws ServiceException;

	/**
	 * Add new rate at users rates list.
	 * 
	 * @param gameId
	 *            Game id for which the rate is make.
	 * @param user
	 *            {@link User} who makes a rate.
	 * @param choice
	 *            User predication.
	 * @param rateCoefficient
	 *            The coefficient on which the rateMoney is multiplied in the
	 *            case of winning.
	 * @param rateMoney
	 *            Amount of money set by the user.
	 * @return Success of error message at json format.
	 * @throws ServiceException
	 */
	String makeRate(int gameId, User user, RateChoice choice, double rateCoefficient, double rateMoney)
			throws ServiceException;
}
