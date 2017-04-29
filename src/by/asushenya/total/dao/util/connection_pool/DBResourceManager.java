package by.asushenya.total.dao.util.connection_pool;

import java.util.ResourceBundle;

/**
 * Get access to database resources.
 * 
 * @author Artyom Sushenya
 *
 */
public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("resource.database");

	private DBResourceManager() {
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
