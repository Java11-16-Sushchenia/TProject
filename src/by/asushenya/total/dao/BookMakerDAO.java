package by.asushenya.total.dao;

import by.asushenya.total.dao.exception.DAOException;

/**
 * Provides the actions available to the bookmaker.
 * 
 * @author Artyom Sushenya
 *
 */
public interface BookMakerDAO {
	/**
	 * Change {@link Game} coefficients by some game id.
	 * 
	 * @param gameId
	 *            Changing game id
	 * @param k1
	 *            First team win coefficient
	 * @param kx
	 *            Draw coefficient
	 * @param k2
	 *            Second team win coefficient
	 * @throws DAOException
	 */
	void setNewGameCoefficients(int gameId, double k1, double kx, double k2) throws DAOException;

	/**
	 * Make game invisible for users.
	 * 
	 * @param gameId
	 *            Id of invisible game.
	 * @throws DAOException
	 */
	void makeGameInvisible(int gameId) throws DAOException;
}
