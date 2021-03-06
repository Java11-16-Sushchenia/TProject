package by.asushenya.total.controller.ajax_controller.ajax_command;


/**
 * Contains all commands that {@link AJAXController} can execute
 * @author Artyom Asushenya 
 *
 */
public enum AJAXCommandName {
	REGISTRATION_USER_AJAX_COMMAND,
	AUTHORIZATION_SIGN_IN_AJAX_COMMAND,
	MAKE_RATE_AJAX_COMMAND,
	SET_NEW_GAME_COEFFICIENTS_AJAX_COMMAND,
	MAKE_GAME_INVISIBLE_AJAX_COMMAND,
	GET_TEAMS_BY_GAME_KIND_AJAX_COMMAND,
	ADD_NEW_GAME_AJAX_COMMAND,	
	BLOCK_USER_AJAX_COMMAND,
	UNBLOCK_USER_AJAX_COMMAND,
	
	NO_SUCH_COMMAND
}
