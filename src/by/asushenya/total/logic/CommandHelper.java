package by.asushenya.total.logic;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.logic.impl.GetAllGamesCommand;
import by.asushenya.total.logic.impl.GetAllTeamsCommand;
import by.asushenya.total.logic.impl.GetAllUserRatesCommand;
import by.asushenya.total.logic.impl.GetAllUsersCommand;
import by.asushenya.total.logic.impl.GetGamesByKindCommand;
import by.asushenya.total.logic.impl.GetPageWithGamesCommand;
import by.asushenya.total.logic.impl.GetPersonalPageCommand;
import by.asushenya.total.logic.impl.LocalizationCommand;
import by.asushenya.total.logic.impl.LogOutCommand;
import by.asushenya.total.logic.impl.MakeRateCommand;
import by.asushenya.total.logic.impl.MakeRateInitCommand;
import by.asushenya.total.logic.impl.RegistrationUserCommand;

public final class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	
	private Map<CommandName, ICommand> commands = new HashMap<>();
	
	public CommandHelper(){

		commands.put(CommandName.REGISTRATION_USER_COMMAND,  new RegistrationUserCommand());
		commands.put(CommandName.GET_ALL_USER_RATES_COMMAND, new GetAllUserRatesCommand());
		commands.put(CommandName.GET_ALL_USERS_COMMAND, new GetAllUsersCommand());
		commands.put(CommandName.GET_ALL_GAMES_COMMAND, new GetAllGamesCommand());
		commands.put(CommandName.GET_ALL_TEAMS_COMMAND, new GetAllTeamsCommand());
		commands.put(CommandName.GET_PAGE_WITH_GAMES_COMMAND, new GetPageWithGamesCommand());
		commands.put(CommandName.MAKE_RATE_COMMAND, new MakeRateCommand());
		commands.put(CommandName.MAKE_RATE_INIT_COMMAND, new MakeRateInitCommand());
		commands.put(CommandName.GET_PERSONAL_PAGE_COMMAND, new GetPersonalPageCommand());
		//commands.put(CommandName.GET_GAMES_BY_KIND_COMMAND, new GetGamesByKindCommand());
		commands.put(CommandName.LOCALIZATION_COMMAND, new LocalizationCommand());
		commands.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
	}
	
	public static CommandHelper getInstance(){
		return instance;
	}
	
	public ICommand getCommand(String commandName){
		

		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		ICommand command;
		if(null != name){
			command = commands.get(name);
		} else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		
		return command;
	}
}
