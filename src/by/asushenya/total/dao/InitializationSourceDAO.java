package by.asushenya.total.dao;

import by.asushenya.total.dao.exception.DAOException;

/**
 * Data source initialize and destroy management.
 * 
 * @author Artyom Sushenya
 *
 */
public interface InitializationSourceDAO {
	/**
	 * Initialize data source.
	 * 
	 * @throws DAOException
	 */
	void initSource() throws DAOException;

	/**
	 * Destroy data source.
	 * 
	 * @throws DAOException
	 */
	void destroySource() throws DAOException;
}
