package by.asushenya.total.bean.util;

import by.asushenya.total.bean.User;

public class UserServiceObject {
	private User user;
	private String jsonWithErrors;
	private String jsonWithSuccess;

	public UserServiceObject() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJsonWithErrors() {
		return jsonWithErrors;
	}

	public void setJsonWithErrors(String jsonWithErrors) {
		this.jsonWithErrors = jsonWithErrors;
	}

	public String getJsonWithSuccess() {
		return jsonWithSuccess;
	}

	public void setJsonWithSuccess(String jsonWithSuccess) {
		this.jsonWithSuccess = jsonWithSuccess;
	}

}
