package by.asushenya.total.service.impl;

import org.apache.log4j.Logger;

import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.exception.InvalidArgumentServiceException;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

/**
 * Implements {@link BookMakerService} interface.
 * 
 * @author Artyom Sushenya
 *
 */
public class BookMakerServiceImpl implements BookMakerService {

	private static final Logger log = Logger.getLogger(BookMakerServiceImpl.class);

	@Override
	public void setNewGameCoefficients(int gameId, double k1, double kx, double k2) throws ServiceException {

		if (!Validator.validateId(gameId)) {
			log.info("invalid game id");
			throw new InvalidArgumentServiceException("invalid game id");
		}
		if (!Validator.validateСoefficient(k1)) {
			log.info("invalid game k1 coefficient");
			throw new InvalidArgumentServiceException("invalid game k1 coefficient");
		}
		if (!Validator.validateСoefficient(kx)) {
			log.info("invalid game kx coefficient");
			throw new InvalidArgumentServiceException("invalid game kx coefficient");
		}
		if (!Validator.validateСoefficient(k2)) {
			log.info("invalid game k2 coefficient");
			throw new InvalidArgumentServiceException("invalid game k2 coefficient");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();

		try {
			bookMakerDAO.setNewGameCoefficients(gameId, k1, kx, k2);
		} catch (DAOException e) {
			throw new ServiceException("can't set new game coefficients", e);
		}
	}

	@Override
	public void makeGameInvisible(int gameId) throws ServiceException {

		if (!Validator.validateId(gameId)) {
			log.info("invalid game id");
			throw new InvalidArgumentServiceException("invalid game id");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();
		try {
			bookMakerDAO.makeGameInvisible(gameId);
		} catch (DAOException e) {
			throw new ServiceException("can't make game invisible", e);
		}
	}
}
