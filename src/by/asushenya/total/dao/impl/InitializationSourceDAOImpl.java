package by.asushenya.total.dao.impl;

import java.io.IOException;

import by.asushenya.total.dao.InitializationSourceDAO;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class InitializationSourceDAOImpl implements InitializationSourceDAO{

	public void initSource() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		}

	}

	public void destroySource() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.close();
		} catch (IOException e) {
			throw new DAOException(e);
		}

	}
}
