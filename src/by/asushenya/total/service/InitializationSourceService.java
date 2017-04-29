package by.asushenya.total.service;

import by.asushenya.total.service.exception.ServiceException;

/**
 * This class manage data source initialization.
 * 
 * @author Artyom Sushenya
 *
 */
public interface InitializationSourceService {
	/**
	 * Initialize data source.
	 * 
	 * @throws ServiceException
	 */
	void initSource() throws ServiceException;

	/**
	 * Destroy data source.
	 * 
	 * @throws ServiceException
	 */
	void destroySource() throws ServiceException;
}
