package by.asushenya.total.service.impl;

import by.asushenya.total.dao.InitializationSourceDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.InitializationSourceService;
import by.asushenya.total.service.exception.ServiceException;

public class InitializationSourceServiceImpl implements InitializationSourceService {

	public void initSource() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		InitializationSourceDAO initializationDAO = daoFactory.getInitializationSourceDAO();

		try {
			initializationDAO.initSource();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

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
