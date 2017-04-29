package by.asushenya.total.service;

import by.asushenya.total.service.exception.ServiceException;

/**
 * Contains all methods which bookmaker can use.
 * 
 * @author Artyom Sushenya
 *
 */
public interface BookMakerService {
	/**
	 * Change coefficients of some game at database by game id.
	 * 
	 * @param gameId
	 *            ID of changeable game.
	 * @param k1
	 *            {@link Game} first team win coefficient.
	 * @param kx
	 *            {@link Game} draw coefficient.
	 * @param k2
	 *            {@link Game} second team win coefficient.
	 * @throws ServiceException
	 */
	void setNewGameCoefficients(int gameId, double k1, double kx, double k2) throws ServiceException;

	/**
	 * Hide some game for user by game id.
	 * 
	 * @param gameId
	 *            Hidden game id.
	 * @throws ServiceException
	 */
	void makeGameInvisible(int gameId) throws ServiceException;

}
