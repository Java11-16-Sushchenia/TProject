package by.asushenya.total.service;

import java.sql.Timestamp;
import java.util.List;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.service.exception.ServiceException;

/**
 * Contains all methods which administrator can use.
 * 
 * @author Artyom Sushenya
 *
 */
public interface AdminService {
	/**
	 * Return {@link UserPage} object from dao with users objects.
	 * 
	 * @param page
	 *            Ordination number of users page.
	 * @param usersPerPage
	 *            Number of {@link User}s at one page.
	 * @return {@link UserPage} object which contains list of {@link User}s or
	 *         error message.
	 * @throws ServiceException
	 */
	UsersPage getUsersPage(int page, int usersPerPage) throws ServiceException;

	/**
	 * Return List of {@link Game}s by some {@link GameKind}
	 * 
	 * @param gameKind
	 *            Parameter of games selecting.
	 * @param local
	 *            Current user language parameters
	 * @return List of Games by some {@link GameKind}
	 * @throws ServiceException
	 */
	List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws ServiceException;

	/**
	 * Add new game to database.
	 * 
	 * @param gameKind
	 *            {@link GameKind} of new {@link Game}.
	 * @param firstTeamName
	 *            Name of first team at game.
	 * @param secondTeamName
	 *            Name of second team at game.
	 * @param gameDate
	 *            Date of {@link Game}.
	 * @param k1
	 *            {@link Game} first team win coefficient.
	 * @param kx
	 *            {@link Game} draw coefficient.
	 * @param k2
	 *            {@link Game} second team win coefficient.
	 * @param local
	 *            Current user language parameters.
	 * @return Success of failure game adding message in json format.
	 * @throws ServiceException
	 */
	String addNewGame(GameKind gameKind, String firstTeamName, String secondTeamName, Timestamp gameDate, double k1,
			double kx, double k2, String local) throws ServiceException;

	/**
	 * Forbid user access to system by some id.
	 * 
	 * @param userId
	 *            Blocking {@link User} id.
	 * @return Success of failure user blocking message in json format.
	 * @throws ServiceException
	 */
	String blockUser(int userId) throws ServiceException;

	/**
	 * Grant user access to system by some id.
	 * 
	 * @param userId
	 *            Unblocking {@link User} id.
	 * @return Success of failure user blocking message in json format.
	 * @throws ServiceException
	 */
	String unblockUser(int userId) throws ServiceException;
}
