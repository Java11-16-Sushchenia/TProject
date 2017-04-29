package by.asushenya.total.service.impl;

import by.asushenya.total.dao.InitializationSourceDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.InitializationSourceService;
import by.asushenya.total.service.exception.ServiceException;

/**
 * Implements {@link InitializationSourceService} interface.
 * 
 * @author Artyom Sushenya
 *
 */
public class InitializationSourceServiceImpl implements InitializationSourceService {
	@Override
	public void initSource() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		InitializationSourceDAO initializationDAO = daoFactory.getInitializationSourceDAO();

		try {
			initializationDAO.initSource();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void destroySource() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		InitializationSourceDAO initializationDAO = daoFactory.getInitializationSourceDAO();

		try {
			initializationDAO.destroySource();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
