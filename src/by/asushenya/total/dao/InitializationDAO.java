package by.asushenya.total.dao;

import by.asushenya.total.dao.exception.DAOException;

public interface InitializationDAO {
	void initSource() throws DAOException;
	void destroySource() throws DAOException;
}
