package by.asushenya.total.dao.util.connection_pool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;

public class ConnectionPool implements Closeable {
	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

	private static final ConnectionPool instance = new ConnectionPool();
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;

	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;

	private ConnectionPool() {
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);

		try {
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
		} catch (NumberFormatException e) {
			this.poolsize = 6;
		}
	}

	public void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);

		try {
			Class.forName(driver);
			for (int i = 0; i < poolsize; i++) {
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}

	}

	public Connection take() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}

	public void free(Connection connection) throws InterruptedException, DAOException {
		if (connection == null) {
			throw new DAOException("Connection is null");
		}

		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(busyConnection);
		listConnection.addAll(freeConnection);

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				LOGGER.error("Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				LOGGER.error("PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				LOGGER.error("Statement isn't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement ps) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				LOGGER.error("PrepareStatement ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				LOGGER.error("Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				LOGGER.error("PrepareStatement ins't closed", e);
			}
		}

	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				LOGGER.error("PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				LOGGER.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				LOGGER.error("Statement isn't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error("ResultSet ins't closed", e);
			}
		}
	}

}
