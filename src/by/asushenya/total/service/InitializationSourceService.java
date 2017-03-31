package by.asushenya.total.service;

import by.asushenya.total.service.exception.ServiceException;

public interface InitializationSourceService {
	void initSource() throws ServiceException;

	void destroySource() throws ServiceException;
}
