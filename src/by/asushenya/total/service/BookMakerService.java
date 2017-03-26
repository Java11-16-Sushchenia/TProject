package by.asushenya.total.service;

import by.asushenya.total.service.exception.ServiceException;

public interface BookMakerService {
	//String getPageWithGames() throws ServiceException;
	void setNewGameCoefficients(int gameId,
								double k1, 
								double kx,
								double k2)
						 			throws ServiceException;
	void makeGameInvisible(int gameId) 
									throws ServiceException;
	
	
}
