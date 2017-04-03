package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.dao.exception.DAOException;

public interface AdminDAO {

	void addGame(Game game, String local) throws DAOException;

	List<User> getUsersForPage(int page, int usersPerPage) throws DAOException;

	int getUsersRecordsCount() throws DAOException;

	List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws DAOException;
	
	void blockUser(User user) throws DAOException;
	
	void unblockUser(User user) throws DAOException;
}
