package by.asushenya.total.controller;

/**
 * Contain all names of .jsp pages
 * 
 * @author Artyom Suschenya
 *
 */

public final class JspPageName {
	private JspPageName() {
	};

	public static final String USER_PAGE = "jsp/userPage.jsp";
	public static final String ADMIN_PAGE = "jsp/adminPage.jsp";
	public static final String BOOKMAKER_PAGE = "jsp/bookmakerPage.jsp";
	public static final String INDEX_PAGE = "index.jsp";
	public static final String ERROR_PAGE = "errorPage";

	public static final String REDIRECT_TO_INDEX_PAGE = "redirectToIndexPage";
	public static final String REGISTRATION_PAGE = "registrationPage";
	public static final String REDIRECT_TO_INDEX_WITH_FOOTBALL_GAME = "redirectToIndexWithFootballGame";
	public static final String REDIRECT_TO_INDEX_WITH_BASKETBALL_GAME = "redirectToIndexWithBasketballGame";
	public static final String REDIRECT_TO_INDEX_WITH_HOCKEY_GAME = "redirectToIndexWithHockeyGame";

	public static final String REDIRECT_TO_ADMIN_PAGE = "redirectToAdminPage";
	public static final String REDIRECT_TO_BOOKMAKER_PAGE = "redirectToBookmakerPage";
	public static final String REDIRECT_TO_USER_PAGE = "redirectToUserPage";
}
