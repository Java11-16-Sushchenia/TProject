package by.asushenya.total.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

	private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9_]{5,15}$";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String TEAM_NAME_PATTERN = "^[A-ZА-Я][A-ZА-Яa-zа-я\\s]{4,30}$";

	private Validator() {
	}

	/**
	 * Validate login with regular expression
	 * 
	 * @param username
	 *            username for validation
	 * @return true valid username, false invalid username
	 */
	public static boolean validateСoefficient(final double coefficient) {
		if (coefficient < 1.1D) {
			return false;
		}
		if (coefficient >= 50.1D) {
			return false;
		}

		return true;
	}

	public static boolean validatePageNumber(final int pageNumber) {
		if (pageNumber < 0) {
			return false;
		}
		return true;
	}
	
	public static boolean validateMoney(final double money) {
		if (money < 0) {
			return false;
		}
		return true;
	}

	public static boolean validateId(final int id) {
		if (id < 0) {
			return false;
		}
		return true;
	}

	public static boolean validateLogin(final String login) {
		Pattern pattern = Pattern.compile(LOGIN_PATTERN);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}

	public static boolean validatePassword(final String password) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean validateEmail(final String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateTeam(final String teamName) {
		Pattern pattern = Pattern.compile(TEAM_NAME_PATTERN);
		Matcher matcher = pattern.matcher(teamName);
		return matcher.matches();
	}
}
