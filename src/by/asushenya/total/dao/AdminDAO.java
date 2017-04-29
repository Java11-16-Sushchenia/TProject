package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;

/**
 * Provides the actions available to the admin.
 * 
 * @author Artyom Sushenya
 *
 */
public interface AdminDAO {
	/**
	 * Add new game at database table. After adding this game is available for
	 * rate making.
	 * 
	 * @param game
	 *            {@link Game} object with game data
	 * @param local
	 *            Current user language parameters
	 * @throws DAOException
	 */
	void addGame(Game game, String local) throws DAOException;

	/**
	 * Return some part of {@link User} from database for one page at view.
	 * 
	 * @param page
	 *            Page sequence number
	 * @param usersPerPage
	 *            Number of {@link User}s at one page
	 * @return List of {@link User}
	 * @throws DAOException
	 */

	List<User> getUsersForPage(int page, int usersPerPage) throws DAOException;

	/**
	 * Return number of users at database.
	 * 
	 * @return Number of {@link User}s at user table
	 * @throws DAOException
	 */
	int getUsersRecordsCount() throws DAOException;

	/**
	 * Return all teams of some {@link GameKind} from database.
	 *
	 * @param gameKind
	 *            Certain {@link GameKind}
	 * @param local
	 *            Current user language parameters
	 * @return List of {@link Team} related to some {@link GameKind}
	 * @throws DAOException
	 */
	List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws DAOException;

	/**
	 * Block some user at system. After calling this method some user can't sign
	 * in to the system.
	 * 
	 * @param user
	 *            blocking {@link User}
	 * @throws DAOException
	 */
	void blockUser(User user) throws DAOException;

	/**
	 * Unblock some user at system. After calling this method some user can sign
	 * in to the system.
	 * 
	 * @param user
	 *            unblocking {@link User}
	 * @throws DAOException
	 */
	void unblockUser(User user) throws DAOException;
}
