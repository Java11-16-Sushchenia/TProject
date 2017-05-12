package by.asushenya.total.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.ConnectionPoolException;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.impl.UserDAOImpl;
import by.asushenya.total.dao.util.connection_pool.ConnectionPool;

public class UserDAOImplTest {

	@Test
	public void testFindUserByEmail() {

		String userEmail = "superUser@gmail.com";
		String userLogin = "superUser";
		UserDAOImpl test = new UserDAOImpl();
		User selectedUser = null;
		try {
			ConnectionPool.getInstance().init();
			selectedUser = test.findUserByEmail(userEmail);

		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		assertEquals(1, selectedUser.getId());
		assertEquals(userEmail, selectedUser.getEmail());
		assertEquals(userLogin, selectedUser.getLogin());
	}

	@Test
	public void testFindUserByLogin() {
		String userEmail = "superUser@gmail.com";
		String userLogin = "superUser";
		UserDAOImpl test = new UserDAOImpl();
		User selectedUser = null;
		try {
			ConnectionPool.getInstance().init();
			selectedUser = test.findUserByLogin(userLogin);

		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		assertEquals(selectedUser.getId(), 1);
		assertEquals(selectedUser.getEmail(), userEmail);
		assertEquals(selectedUser.getLogin(), userLogin);
	}

}
