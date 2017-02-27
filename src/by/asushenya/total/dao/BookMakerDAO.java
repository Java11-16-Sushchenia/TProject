package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.dao.exception.DAOException;

public interface BookMakerDAO {
	List<Game> getAllGames() throws DAOException;
	void addGame(Game game) throws DAOException;
	List<Team> getAllTeams() throws DAOException;
}
