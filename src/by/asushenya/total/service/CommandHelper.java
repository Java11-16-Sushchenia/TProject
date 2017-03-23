package by.asushenya.total.service;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.service.impl.GetAllGamesCommand;
import by.asushenya.total.service.impl.GetAllTeamsCommand;
import by.asushenya.total.service.impl.GetAllUserRatesCommand;
import by.asushenya.total.service.impl.GetAllUsersCommand;
import by.asushenya.total.service.impl.GetPageWithGamesCommand;
import by.asushenya.total.service.impl.GetPersonalPageCommand;
import by.asushenya.total.service.impl.LocalizationCommand;
import by.asushenya.total.service.impl.LogOutCommand;

public final class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	
	private Map<CommandName, ICommand> commands = new HashMap<>();
	
	public CommandHelper(){
		 //сделать пагинацию для ставок пользователя
		commands.put(CommandName.GET_ALL_USER_RATES_COMMAND, new GetAllUserRatesCommand());
		commands.put(CommandName.GET_ALL_USERS_COMMAND, new GetAllUsersCommand());
		//commands.put(CommandName.GET_ALL_GAMES_COMMAND, new GetAllGamesCommand());
		//commands.put(CommandName.GET_ALL_TEAMS_COMMAND, new GetAllTeamsCommand());
		commands.put(CommandName.GET_PAGE_WITH_GAMES_COMMAND, new GetPageWithGamesCommand());
		commands.put(CommandName.GET_PERSONAL_PAGE_COMMAND, new GetPersonalPageCommand());
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
