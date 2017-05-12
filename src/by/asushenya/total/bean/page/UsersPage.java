package by.asushenya.total.bean.page;

import java.util.List;

import by.asushenya.total.bean.User;

/**
 * 
 * Transfer list with {@link User} and number of {@link User} at page from
 * services to command
 *
 * @author Artyom Suschenya
 */
public class UsersPage {
	private List<User> usersList;
	private int numberOfPages;

	public UsersPage() {
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
