package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import by.asushenya.total.dao.util.ConnectionManager;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class AdminDAOImpl implements AdminDAO {

	private static final Logger log = Logger.getLogger(AdminDAOImpl.class);

	private final static String getAllUsersQuerry = "select id, login, password, email, role, cash from user limit ? , ?";
	private static final String getAllUsersCountQuerry = "select count(*) `users_count` from user";
	private static final String getAllTeamsRuQuerry = "select id, name as `name` from team where game_kind = ?";
	private static final String getAllTeamsEnQuerry = "select id, name_en `name` from team where game_kind = ?";

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
		}catch (SQLException e) {
			log.error("can't get gameId by Name", e);
		} finally {
			pool.closeConnection(con, ps,rs);
		}
		return teamId;
	}


	public List<User> getUsersForPage(int page, int usersPerPage) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> list = new ArrayList<User>();

		try {
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(getAllUsersQuerry);

			ps.setInt(1, page);
			ps.setInt(2, usersPerPage);

			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(UserRole.valueOf(rs.getString("role").toUpperCase()));
				user.setCash(rs.getFloat("cash"));
				list.add(user);
			}
		} catch (SQLException e) {
			log.error("can't get users for page", e);
		} finally {
			ConnectionManager.disconnectFromDB(rs, ps, con);
		}
		return list;
	}

	public int getUsersRecordsCount() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int usersCount = 0;

		try {
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(getAllUsersCountQuerry);

			rs = ps.executeQuery();

			while (rs.next()) {
				usersCount = rs.getInt("users_count");
			}
		} catch (SQLException e) {
			log.error("can't get users count", e);
			throw new DAOException("can't get users count", e);

		} finally {
			ConnectionManager.disconnectFromDB(rs, ps, con);
		}
		return usersCount;
	}

	public List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Team> teamsOfSomeGameKind = new ArrayList<Team>();

		try {
			con = ConnectionManager.getDBTotalizatorConnection();

			if (local.equals(RequestParameterName.SESSION_LOCAL_RU)) {
				ps = con.prepareStatement(getAllTeamsRuQuerry);
			} else if (local.equals(RequestParameterName.SESSION_LOCAL_EN)) {
				ps = con.prepareStatement(getAllTeamsEnQuerry);
			}

			ps.setString(1, gameKind.toString().toLowerCase());
			rs = ps.executeQuery();

			while (rs.next()) {
				Team team = new Team();

				team.setId(rs.getInt("id"));
				team.setName(rs.getString("name"));

				teamsOfSomeGameKind.add(team);
			}
		} catch (SQLException e) {
			log.error("can't get teams by game kind", e);
			throw new DAOException("can't get teams", e);

		} finally {
			ConnectionManager.disconnectFromDB(rs, ps, con);
		}
		return teamsOfSomeGameKind;
	}
}
