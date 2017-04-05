package by.asushenya.total.dao.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import by.asushenya.total.dao.InitializationSourceDAO;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class InitializationSourceDAOImpl implements InitializationSourceDAO {

	private static final Logger log = Logger.getLogger(InitializationSourceDAOImpl.class);
	
	public void initSource() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.init();
		} catch (ConnectionPoolException e){
			log.error("can't initialize connection pool",e);
			throw new DAOException(e);
		}

	}

	public void destroySource() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.close();
		} catch (IOException e) {
			log.error("can't destroy connection pool",e);
			throw new DAOException(e);
		}

	}
}
