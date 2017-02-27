package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;

public interface AdminDAO {
	List<User> getAllUsers() throws DAOException;
}
