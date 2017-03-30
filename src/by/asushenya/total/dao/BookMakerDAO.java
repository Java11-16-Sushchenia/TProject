package by.asushenya.total.dao;

import by.asushenya.total.dao.exception.DAOException;

public interface BookMakerDAO {
	//List<Game> getAllGames(String local) throws DAOException;

	void setNewGameRates(int gameId, double k1, double kx, double k2) throws DAOException;

	void makeGameInvisible(int gameId) throws DAOException;
}
