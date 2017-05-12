package by.asushenya.total.controller;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.bean.util.UserRole;

public class PersonalPagesHelper {
	private static final PersonalPagesHelper instance = new PersonalPagesHelper();

	private Map<UserRole, String> pages = new HashMap<UserRole, String>();

	public PersonalPagesHelper() {
		pages.put(UserRole.ADMIN, JspPageName.REDIRECT_TO_ADMIN_PAGE);
		pages.put(UserRole.BOOKMAKER, JspPageName.REDIRECT_TO_BOOKMAKER_PAGE);
		pages.put(UserRole.USER, JspPageName.REDIRECT_TO_USER_PAGE);
	}

	public static PersonalPagesHelper getInstance() {
		return instance;
	}

	public String getPersonalPage(UserRole role) {
		return pages.get(role);
	}

}
