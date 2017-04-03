package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.CollumnName;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.sql_query.AdminQuery;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class AdminDAOImpl implements AdminDAO {

	private static final Logger log = Logger.getLogger(AdminDAOImpl.class);

	public void addGame(Game game, String local) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();
			ps = con.prepareStatement(AdminQuery.addNewGameQuerry);

			ps.setString(1, game.getGameKind().toString().toLowerCase());
			ps.setInt(2, getTeamIdByName(game.getFirstTeam(), local));
			ps.setInt(3, getTeamIdByName(game.getSecondTeam(), local));
			ps.setTimestamp(4, game.getDate());
			ps.setDouble(5, game.getK1());
			ps.setDouble(6, game.getKx());
			ps.setDouble(7, game.getK2());

			ps.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			throw new DAOException("DAOException addNewGame: " + e.getMessage());

		} finally {
			pool.closeConnection(con, ps);
		}
	}

	private int getTeamIdByName(String teamName, String local) throws DAOException {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int teamId = 0;

		try {
			con = pool.take();

			if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
				ps = con.prepareStatement(AdminQuery.GET_TEAM_ID_BY_NAME_RU);
			} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
				ps = con.prepareStatement(AdminQuery.GET_TEAM_ID_BY_NAME_EN);
			}

			ps.setString(1, teamName);

			rs = ps.executeQuery();

			while (rs.next()) {
				teamId = rs.getInt(CollumnName.ID);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get gameId by Name", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return teamId;
	}

	public List<User> getUsersForPage(int page, int usersPerPage) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> list = new ArrayList<User>();

		try {
			con = pool.take();
			ps = con.prepareStatement(AdminQuery.GET_ALL_USERS);

			ps.setInt(1, page);
			ps.setInt(2, usersPerPage);

			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt(CollumnName.ID));
				user.setLogin(rs.getString(CollumnName.LOGIN));
				user.setPassword(rs.getString(CollumnName.PASSWORD));
				user.setEmail(rs.getString(CollumnName.EMAIL));
				user.setRole(UserRole.valueOf(rs.getString(CollumnName.ROLE).toUpperCase()));
				user.setCash(rs.getFloat(CollumnName.CASH));
				user.setIsVisible(rs.getInt("is_visible"));
				list.add(user);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get users for page", e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return list;
	}

	public int getUsersRecordsCount() throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int usersCount = 0;

		try {
			con = pool.take();
			ps = con.prepareStatement(AdminQuery.GET_ALL_USERS_COUNT);

			rs = ps.executeQuery();

			while (rs.next()) {
				usersCount = rs.getInt(CollumnName.USERS_COUNT);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get users count", e);
			throw new DAOException("can't get users count", e);

		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return usersCount;
	}

	public List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Team> teamsOfSomeGameKind = new ArrayList<Team>();

		try {
			con = pool.take();

			if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
				ps = con.prepareStatement(AdminQuery.GET_TEAMS_BY_GAME_KIND_RU);
			} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
				ps = con.prepareStatement(AdminQuery.GET_TEAMS_BY_GAME_KIND_EN);
			}

			ps.setString(1, gameKind.toString().toLowerCase());
			rs = ps.executeQuery();

			while (rs.next()) {
				Team team = new Team();

				team.setId(rs.getInt(CollumnName.ID));
				team.setName(rs.getString(CollumnName.NAME));

				teamsOfSomeGameKind.add(team);
			}
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			log.error("can't get teams by game kind", e);
			throw new DAOException("can't get teams", e);

		} finally {
			pool.closeConnection(con, ps, rs);
		}
		return teamsOfSomeGameKind;
	}

	@Override
	public void blockUser(User user) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();
			ps = con.prepareStatement(AdminQuery.BLOCK_USER);

			ps.setInt(1, user.getId());

			ps.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			throw new DAOException("DAOException addNewGame: " + e.getMessage());
		} finally {
			pool.closeConnection(con, ps);
		}
	}

	@Override
	public void unblockUser(User user) throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.take();
			ps = con.prepareStatement(AdminQuery.UNBLOCK_USER);

			ps.setInt(1, user.getId());

			ps.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("connection pool problem", e);
			throw new DAOException("connection pool problem", e);
		} catch (SQLException e) {
			throw new DAOException("DAOException addNewGame: " + e.getMessage());
		} finally {
			pool.closeConnection(con, ps);
		}
	}
}
