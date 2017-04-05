package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.CollumnName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.sql_query.UserQuery;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

import org.apache.log4j.Logger;

public class UserDAOImpl implements UserDAO {

	private static final Logger log = Logger.getLogger(UserDAOImpl.class);

	public User findUserByEmail(String email) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;
		try {
			con = pool.take();

			ps = con.prepareStatement(UserQuery.FIND_USER_BY_EMAIL);

			ps.setString(1, email);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(CollumnName.ID));
				user.setLogin(rs.getString(CollumnName.LOGIN));
				user.setEmail(rs.getString(CollumnName.EMAIL));
				user.setPassword(rs.getString(CollumnName.PASSWORD));
				user.setRole(UserRole.valueOf(rs.getString(CollumnName.ROLE).toUpperCase()));
				user.setCash(rs.getFloat(CollumnName.CASH));
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't find user by email", e);
			throw new DAOException("user finding by email problem", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return user;
	}

	public User findUserByLogin(String userLogin) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;
		try {
			con = pool.take();
			ps = con.prepareStatement(UserQuery.FIND_USER_BY_LOGIN);
			ps.setString(1, userLogin);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(CollumnName.ID));
				user.setLogin(rs.getString(CollumnName.LOGIN));
				user.setEmail(rs.getString(CollumnName.EMAIL));
				user.setPassword(rs.getString(CollumnName.PASSWORD));
				user.setRole(UserRole.valueOf(rs.getString(CollumnName.ROLE).toUpperCase()));
				user.setCash(rs.getFloat(CollumnName.CASH));
				user.setIsVisible(rs.getInt("is_visible"));
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't find user by login", e);
			throw new DAOException("user finding by login problem", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return user;
	}

	public void registeredNewUser(User user) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();

			ps = con.prepareStatement(UserQuery.INSERT_INTO_USER);

			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, String.valueOf(UserRole.USER));
			ps.setDouble(5, 100.0D);

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't register new user", e);
			throw new DAOException("user registration problem",e);

		} finally {
			pool.closeConnection(con, ps);
		}
	}

	@Override
	public boolean makeRate(Rate rate) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();

			ps = con.prepareStatement(UserQuery.INSERT_INTO_RATE);

			ps.setInt(1, rate.getUser().getId());
			ps.setInt(2, rate.getGame().getId());
			ps.setDouble(3, rate.getMoney());
			ps.setString(4, rate.getChoice().toString());
			ps.setDouble(5, rate.getGameCoefficient());

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't make rate", e);
			throw new DAOException("DAOException cant make rate: ",e);
		} finally {
			pool.closeConnection(con, ps);
		}
		return true;
	}

	public List<Game> getGamesForPage(int offset, int noOfRecords, GameKind gameKind, String local)
			throws DAOException {

		ConnectionPool pool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Game> list = new ArrayList<Game>();

		try {
			con = pool.take();

			if (gameKind != null) {

				if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
					ps = con.prepareStatement(UserQuery.GET_GAMES_BY_GAME_KIND_RU);
				} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
					ps = con.prepareStatement(UserQuery.GET_GAMES_BY_GAME_KIND_EN);
				}
				ps.setString(1, gameKind.toString().toLowerCase());
				ps.setInt(2, offset);
				ps.setInt(3, noOfRecords);
			} else if (gameKind == null) {
				if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
					ps = con.prepareStatement(UserQuery.GET_ALL_GAMES_FOR_PAGE_RU);
				} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
					ps = con.prepareStatement(UserQuery.GET_ALL_GAMES_FOR_PAGE_EN);
				}
				ps.setInt(1, offset);
				ps.setInt(2, noOfRecords);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("id"));
				game.setGameKind(GameKind.valueOf(rs.getString("game_kind").toUpperCase()));

				game.setFirstTeam(rs.getString("team_1"));
				game.setSecondTeam(rs.getString("team_2"));
				game.setDate(rs.getTimestamp("date"));
				game.setK1(rs.getDouble("k1"));
				game.setKx(rs.getDouble("kx"));
				game.setK2(rs.getDouble("k2"));
				list.add(game);
			}

		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get games for page", e);
			throw new DAOException("geting games for page error",e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return list;
	}

	public int getGamesRecordsByGameKindCount(GameKind gameKind) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int gamesCount = 0;

		try {
			con = pool.take();

			if (gameKind != null) {
				ps = con.prepareStatement(UserQuery.GET_ALL_GAMES_BY_GAME_KIND_COUNT);
				ps.setString(1, gameKind.toString().toLowerCase());
			} else {
				ps = con.prepareStatement(UserQuery.GET_ALL_GAMES_COUNT);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				gamesCount = rs.getInt(CollumnName.GAMES_COUNT);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get games count by game kind", e);
			throw new DAOException("can't get games count", e);

		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return gamesCount;
	}

	public List<Rate> getRatesForPage(User user, int page, int ratesPerPage, String local) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Rate> list = new ArrayList<Rate>();

		try {
			con = pool.take();

			if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
				ps = con.prepareStatement(UserQuery.GET_ALL_USER_RATES_RU);
			} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
				ps = con.prepareStatement(UserQuery.GET_ALL_USER_RATES_EN);
			}

			ps.setInt(1, user.getId());
			ps.setInt(2, page);
			ps.setInt(3, ratesPerPage);

			rs = ps.executeQuery();

			while (rs.next()) {
				Rate rate = new Rate();

				rate.setId(rs.getInt("id"));

				Game game = new Game();
				game.setFirstTeam(rs.getString(CollumnName.TEAM_1));
				game.setSecondTeam(rs.getString(CollumnName.TEAM_2));
				rate.setGame(game);

				rate.setDate(rs.getTimestamp(CollumnName.DATE));

				rate.setMoney(rs.getDouble(CollumnName.MONEY));
				rate.setChoice(RateChoice.valueOf(rs.getString(CollumnName.CHOICE)));
				rate.setGameCoefficient(rs.getDouble(CollumnName.GAME_COEFFICIENT));
				rate.setProfit(rs.getDouble(CollumnName.PROFIT));
				rate.setState(rs.getByte(CollumnName.IS_SUCCESS));

				list.add(rate);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get rates for page", e);
			throw new DAOException("can't get rates for page", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return list;
	}

	public int getRatesRecordsCountOfUser(User user) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int ratesCount = 0;

		try {
			con = pool.take();

			ps = con.prepareStatement(UserQuery.GET_USER_RATES_COUNT);
			ps.setInt(1, user.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				ratesCount = rs.getInt(CollumnName.RATES_COUNT);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get rates count", e);
			throw new DAOException("can't get rates count", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return ratesCount;
	}
}
