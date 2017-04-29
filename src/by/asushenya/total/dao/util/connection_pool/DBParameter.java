package by.asushenya.total.dao.util.connection_pool;

/**
 * Parameters for connection and get access to database.
 * 
 * @author Artyom Sushenya
 *
 */
public class DBParameter {
	private DBParameter() {
	}

	public static final String DB_DRIVER = "db.driver";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_URL = "db.url";
	public static final String DB_POOLSIZE = "db.poolsize";
}
