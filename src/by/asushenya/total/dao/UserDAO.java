package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;

/**
 * Provides the actions available to the admin.
 * 
 * @author Artyom Sushenya
 *
 */
public interface UserDAO {
	/**
	 * Add to database new {@link User}
	 * 
	 * @param user
	 *            New {@link User} object.
	 * @throws DAOException
	 */
	void registeredNewUser(User user) throws DAOException;

	/**
	 * Return {@link User} object by login from database.
	 * 
	 * @param login
	 *            {@link User} finding parameter.
	 * @return {@link User} object with some login.
	 * @throws DAOException
	 */
	User findUserByLogin(String login) throws DAOException;

	/**
	 * Return {@link User} object by email from database.
	 * 
	 * @param email
	 *            {@link User} finding parameter.
	 * @return {@link User} object with some email.
	 * @throws DAOException
	 */
	User findUserByEmail(String email) throws DAOException;

	/**
	 * Add new {@link Rate} object at database.
	 * 
	 * @param rate
	 *            Adding {@link Rate} object
	 * @return If adding successful 'true' returning, else false.
	 * @throws DAOException
	 */
	boolean makeRate(Rate rate) throws DAOException;

	/**
	 * Return some part of {@link Game}s from database for one page at view.
	 * 
	 * @param offset
	 *            Position at database table with which start to return
	 *            {@link Game}s. At SQL query it's 'select * from game limit
	 *            offset, noOfRecords.
	 * @param noOfRecords
	 *            Number of returned games.
	 * @param gameKind
	 *            {@link GameKind} of returned {@link Game}s
	 * @param local
	 *            current {@link User} language parameters.
	 * @return noOfRecords from offset position from database.
	 * @throws DAOException
	 */
	List<Game> getGamesForPage(int offset, int noOfRecords, GameKind gameKind, String local) throws DAOException;

	/**
	 * Return number of {@link Game}s from table at database by some
	 * {@link GameKind}
	 * 
	 * @param gameKind
	 *            Parameter of {@link Game}s selection.
	 * @return Numbers of {@link Game}s at at database table of some
	 *         {@link GameKind}
	 * @throws DAOException
	 */
	int getGamesRecordsByGameKindCount(GameKind gameKind) throws DAOException;

	/**
	 * Return some part of {@link Rate}s from database for one page at view.
	 * 
	 * @param user
	 *            {@link Game}s of some user returned from database.
	 * @param page
	 *            Position at database table with which start to return
	 *            {@link Rate}s. At SQL query it's 'select * from rate limit
	 *            page, ratesPerPage.
	 * @param ratesPerPage
	 *            Number of returned {@link Game}s.
	 * @param local
	 *            current {@link User} language parameters.
	 * @throws DAOException
	 */
	List<Rate> getRatesForPage(User user, int page, int ratesPerPage, String local) throws DAOException;

	/**
	 * Return number of {@link Rate}s of some {@link User}
	 * 
	 * @param user
	 *            {@link User} object rates which returned.
	 * @return Number of user {@link Rate}s
	 * @throws DAOException
	 */
	int getRatesRecordsCountOfUser(User user) throws DAOException;

}
