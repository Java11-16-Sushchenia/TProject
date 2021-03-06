package by.asushenya.total.controller;

/**
 * 
 * Contains all parameters names that can be writed at response
 * 
 * @author Artyom Suschenya
 */
public class ResponseParameterName {

	public final static String ERROR_TYPE = "errorType";
	public final static String ERROR_MSSAGE = "errorMessage";
	public final static String SUCCESS = "success";
	public final static String SUCCESS_MESSAGE = "successMessage";

	// make rate ajax command
	public final static String MAKE_RATE_ERROR = "makerateerror";
	public final static String NO_MONEY = "nomoney";
	public final static String LESS_THAN_ZERO = "lessthanzero";
	public final static String RATE_NOT_A_NUMBER = "ratenotanumber";
	public final static String EMPTY_USER = "emptyuser";
	public final static String OK = "ok";
	public final static String EMPTY_GAME = "emptygame";
	public final static String UNKNOWN_ERROR = "unknownerror";
	public final static String INVALID_ID = "invalidid";
	public final static String INVALID_MONEY = "invalidmoney";
	public final static String USER_CASH = "usercash";
	public final static String NOT_USER = "notuser";
	
	// authorize ajax command
	public final static String AUTHORIZATION_ERROR = "authorizationerror";
	public final static String INVALID_LOGIN = "invalidlogin";
	public final static String EMPTY_LOGIN = "emptylogin";
	public final static String INVALID_PASSWORD = "invalidpassword";
	public final static String EMPTY_PASSWORD = "emptypassword";
	public final static String NOT_REGISTRED = "notregistred";
	public final static String USER_ROLE = "userRole";
	public final static String SIGN_IN_ERROR = "signinerror";

	// registrate ajax command
	public final static String REGISTRATION_ERROR = "registrationerror";
	public final static String USER_EXISTS = "userexists";
	public final static String USER_EMAIL_EXISTS = "emailexists";
	public final static String INVALID_EMAIL = "invalidemail";
	public final static String GAME_KIND = "gameKind";

	// USER BLOCK COMMAND
	public final static String USER_BLOCKING_ERROR = "userblockingerror";
	public final static String USER_UNBLOCKING_ERROR = "userunblockingerror";
	public final static String USER_BLOCKED = "userunblockingerror";

	// GET_TEAMS_OF_SOME_GAME_KIND_AJAX_COMMAND
	public final static String TEAMS_ARRAY = "teamsArray";

	// add new game command
	public final static String ADD_NEW_GAME_ERROR = "addnewgameerror";
	public final static String INVALID_TEAM_NAME = "invalidteamname";
	public final static String INVALID_СOEFFICIENT = "invalidcoefficient";

	// change game errors
	public final static String CHANGE_GAME_ERROR = "changegameerror";

	// change game errors
	public final static String MAKE_GAME_INVISIBLE_ERROR = "makegameinvisibleerror";

}
