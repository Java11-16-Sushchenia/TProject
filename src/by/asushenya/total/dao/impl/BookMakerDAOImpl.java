package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.sql_query.BookMakerQuery;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class BookMakerDAOImpl implements BookMakerDAO {

	private static final Logger log = Logger.getLogger(BookMakerDAOImpl.class);

	public void setNewGameRates(int gameId, double k1, double kx, double k2) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();
			ps = con.prepareStatement(BookMakerQuery.UPDATE_GAME_COEFFICIENTS);

			ps.setDouble(1, k1);
			ps.setDouble(2, kx);
			ps.setDouble(3, k2);
			ps.setInt(4, gameId);

			ps.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("DAOException: can't set new game coefficients", e);
			throw new DAOException("DAOException: can't set new game coefficients", e);

		} finally {
			pool.closeConnection(con, ps);
		}
	}

	public void makeGameInvisible(int gameId) throws DAOException {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();
			ps = con.prepareStatement(BookMakerQuery.MAKE_GAME_INVISIBLE);

			ps.setInt(1, gameId);

			ps.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {

			throw new DAOException("DAOException addEquipment: " + e.getMessage());

		} finally {
			pool.closeConnection(con, ps);
		}
	}
}
