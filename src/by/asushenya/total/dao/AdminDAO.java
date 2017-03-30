package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;

public interface AdminDAO {
	List<User> getAllUsers() throws DAOException;

	void addGame(Game game, String local) throws DAOException;

	List<User> getUsersForPage(int page, int usersPerPage) throws DAOException;

	int getUsersRecordsCount() throws DAOException;
}
