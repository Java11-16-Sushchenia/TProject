package by.asushenya.total.service.impl;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.util.Validator;

public class BookMakerServiceImpl implements BookMakerService {

	private static final Logger log = Logger.getLogger(BookMakerServiceImpl.class);

	public void setNewGameCoefficients(int gameId, double k1, double kx, double k2) throws ServiceException {
		
		if(!Validator.validateId(gameId)){
			return;
		}
		if (!Validator.validateСoefficient(k1)) {
			return;
		}
		if (!Validator.validateСoefficient(kx)) {
			return;
		}
		if (!Validator.validateСoefficient(k2)) {
			return;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();

		try {
			bookMakerDAO.setNewGameCoefficients(gameId, k1, kx, k2);
		} catch (DAOException e) {
			log.error("can't set new game coefficients", e);
			throw new ServiceException("can't set new game coefficients", e);
		}
	}

	public void makeGameInvisible(int gameId) throws ServiceException {

		if (!Validator.validateId(gameId)) {
			return;
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		BookMakerDAO bookMakerDAO = daoFactory.getBookMakerDAO();
		try {
			bookMakerDAO.makeGameInvisible(gameId);
		} catch (DAOException e) {
			log.error("can't make game invisible", e);
			throw new ServiceException("can't make game invisible", e);
		}
	}
}
