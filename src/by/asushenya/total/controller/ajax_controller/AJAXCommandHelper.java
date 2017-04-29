package by.asushenya.total.controller.ajax_controller;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.AJAXCommandName;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.GetTeamsByGameKindAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.MakeGameInvisibleAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.MakeRateAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.SetNewGameCoefficientsAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.SignInAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.UnblockUserAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.RegistrationUserAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.AddNewGameAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.BlockUserAJAXCommand;

/**
 * This class provide {@link AJAXController} the necessary command by name
 * 
 * @author Atryom Suschenya
 */
public final class AJAXCommandHelper {
	private static final AJAXCommandHelper instance = new AJAXCommandHelper();
	/**
	 * Contains all commands that {@link AJAXController} can execute
	 */
	private Map<AJAXCommandName, IAJAXCommand> commands = new HashMap<AJAXCommandName, IAJAXCommand>();

	public AJAXCommandHelper() {
		commands.put(AJAXCommandName.REGISTRATION_USER_AJAX_COMMAND, new RegistrationUserAJAXCommand());
		commands.put(AJAXCommandName.AUTHORIZATION_SIGN_IN_AJAX_COMMAND, new SignInAJAXCommand());
		commands.put(AJAXCommandName.MAKE_RATE_AJAX_COMMAND, new MakeRateAJAXCommand());
		commands.put(AJAXCommandName.SET_NEW_GAME_COEFFICIENTS_AJAX_COMMAND, new SetNewGameCoefficientsAJAXCommand());
		commands.put(AJAXCommandName.MAKE_GAME_INVISIBLE_AJAX_COMMAND, new MakeGameInvisibleAJAXCommand());
		commands.put(AJAXCommandName.GET_TEAMS_BY_GAME_KIND_AJAX_COMMAND, new GetTeamsByGameKindAJAXCommand());
		commands.put(AJAXCommandName.ADD_NEW_GAME_AJAX_COMMAND, new AddNewGameAJAXCommand());
		commands.put(AJAXCommandName.BLOCK_USER_AJAX_COMMAND, new BlockUserAJAXCommand());
		commands.put(AJAXCommandName.UNBLOCK_USER_AJAX_COMMAND, new UnblockUserAJAXCommand());
	}

	public static AJAXCommandHelper getInstance() {
		return instance;
	}
	/**
	 * Transform String form of command at {@link IAJAXCommand} 
	 * @param commandName
	 *            name of the command at String
	 * @return ICommand object, that realize some command
	 */
	public IAJAXCommand getCommand(String commandName) {
		AJAXCommandName name = AJAXCommandName.valueOf(commandName.toUpperCase());
		IAJAXCommand command;

		if (null != name) {
			command = commands.get(name);
		} else {
			command = commands.get(AJAXCommandName.NO_SUCH_COMMAND);
		}

		return command;
	}
}
