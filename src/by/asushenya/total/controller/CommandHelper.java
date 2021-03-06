package by.asushenya.total.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.command.impl.ChangeLocalizationCommand;
import by.asushenya.total.controller.command.impl.GetPageWithGamesCommand;
import by.asushenya.total.controller.command.impl.GetPageWithRatesCommand;
import by.asushenya.total.controller.command.impl.GetPageWithUsersCommand;
import by.asushenya.total.controller.command.impl.GetPersonalPageCommand;
import by.asushenya.total.controller.command.impl.InitializeAddNewGamePageCommand;
import by.asushenya.total.controller.command.impl.authorization.SignOut;
import by.asushenya.total.controller.command.impl.initialization.DestroySourceCommand;
import by.asushenya.total.controller.command.impl.initialization.InitializationSourceCommand;
import by.asushenya.total.controller.command.ICommand;

/**
 * This class provide controller the necessary command by name
 * 
 * @author Atryom Suschenya
 */
public final class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	/**
	 * Contains all commands that {@link Controller} can execute
	 */

	private static final Logger log = Logger.getLogger(CommandHelper.class);

	private Map<CommandName, ICommand> commands = new HashMap<CommandName, ICommand>();

	public CommandHelper() {
		commands.put(CommandName.INITIALIZATION_SOURCE_COMMAND, new InitializationSourceCommand());
		commands.put(CommandName.DESTROY_SOURCE_COMMAND, new DestroySourceCommand());
		commands.put(CommandName.GET_PAGE_WITH_GAMES_COMMAND, new GetPageWithGamesCommand());
		commands.put(CommandName.GET_PERSONAL_PAGE_COMMAND, new GetPersonalPageCommand());
		commands.put(CommandName.AUTHORIZATION_USER_SIGN_OUT_COMMAND, new SignOut());
		commands.put(CommandName.CHANGE_LOCALIZATION_COMMAND, new ChangeLocalizationCommand());
		commands.put(CommandName.GET_PAGE_WITH_USERS_COMMAND, new GetPageWithUsersCommand());
		commands.put(CommandName.GET_PAGE_WITH_RATES_COMMAND, new GetPageWithRatesCommand());
		commands.put(CommandName.INITIALIZE_ADD_NEW_GAME_PAGE_COMMAND, new InitializeAddNewGamePageCommand());
	}

	/**
	 * return single instance of {@link CommandHelper}
	 * 
	 * @return return's instance of CommandHelper сlass
	 */

	public static CommandHelper getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param commandName
	 *            name of the command at String
	 * @return ICommand object, that realize some command
	 */
	public ICommand getCommand(String commandName) {

		CommandName name = null;
		ICommand command;

		try {
			name = CommandName.valueOf(commandName.toUpperCase());
		} catch (IllegalArgumentException e) {
			log.error("no such command", e);
		}

		if (null != name) {
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}

		return command;
	}
}
