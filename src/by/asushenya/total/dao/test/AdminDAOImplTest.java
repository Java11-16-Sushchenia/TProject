package by.asushenya.total.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.dao.impl.AdminDAOImpl;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class AdminDAOImplTest {

	@Test
	public void testBlockUser() {
		int blockingUserId = 3;
		String changedUserLogin = "dima";

		AdminDAOImpl test = new AdminDAOImpl();
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

		User blockingUser = new User();
		blockingUser.setId(blockingUserId);

		User changedUser = null;

		try {
			ConnectionPool.getInstance().init();
			test.blockUser(blockingUser);

			changedUser = userDAO.findUserByLogin(changedUserLogin);

		} catch (ConnectionPoolException e) {

			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		assertEquals(0, changedUser.getIsVisible());
	}

	@Test
	public void testUnblockUser() {
		int unblockingUserId = 3;
		String changedUserLogin = "dima";

		AdminDAOImpl test = new AdminDAOImpl();
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

		User unblockingUser = new User();
		unblockingUser.setId(unblockingUserId);

		User changedUser = null;

		try {
			ConnectionPool.getInstance().init();
			test.unblockUser(unblockingUser);

			changedUser = userDAO.findUserByLogin(changedUserLogin);

		} catch (ConnectionPoolException e) {

			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		assertEquals(1, changedUser.getIsVisible());
	}

}
