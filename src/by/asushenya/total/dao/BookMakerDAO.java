package by.asushenya.total.dao;

import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.dao.exception.DAOException;

public interface BookMakerDAO {
	List<Game> getAllGames(String local) throws DAOException;
	//List<Team> getAllTeams() throws DAOException;
	void setNewGameRates(int gameId, double k1, double kx, double k2)throws DAOException;
	void makeGameInvisible(int gameId) throws DAOException;
}
