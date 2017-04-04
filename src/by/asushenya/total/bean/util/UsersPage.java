package by.asushenya.total.bean.util;

import java.util.List;

import by.asushenya.total.bean.User;

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
