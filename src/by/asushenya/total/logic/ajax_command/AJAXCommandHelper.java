package by.asushenya.total.logic.ajax_command;

import java.util.HashMap;
import java.util.Map;

import by.asushenya.total.logic.CommandName;
import by.asushenya.total.logic.ajax_command.impl.MakeRateAJAXCommand;
import by.asushenya.total.logic.ajax_command.impl.SetNewGameRatesAJAXCommand;
import by.asushenya.total.logic.ajax_command.impl.AddNewGameAJAXCommand;
import by.asushenya.total.logic.ajax_command.impl.AuthorizationUserAJAXCommand;
import by.asushenya.total.logic.ajax_command.impl.GetTeamsOfSomeGameKindAJAXCommand;
import by.asushenya.total.logic.ajax_command.impl.MakeGameInvisibleAJAXCommand;


public final class AJAXCommandHelper {
	private static final AJAXCommandHelper instance = new AJAXCommandHelper();
	
	private Map<CommandName,IAJAXCommand> commands = new HashMap<CommandName,IAJAXCommand>();

	
	public AJAXCommandHelper(){
		commands.put(CommandName.MAKE_RATE_COMMAND, new MakeRateAJAXCommand());
		commands.put(CommandName.AUTHORIZATION_USER_COMMAND, new AuthorizationUserAJAXCommand());
		commands.put(CommandName.SET_NEW_GAME_RATES_AJAX_COMMAND, new SetNewGameRatesAJAXCommand());
		commands.put(CommandName.MAKE_GAME_INVISIBLE_AJAX_COMMAND, new MakeGameInvisibleAJAXCommand());
		commands.put(CommandName.ADD_NEW_GAME_AJAX_COMMAND, new AddNewGameAJAXCommand());
		commands.put(CommandName.GET_TEAMS_OF_SOME_GAME_KIND_AJAX_COMMAND, new GetTeamsOfSomeGameKindAJAXCommand());
	}
	
	public static AJAXCommandHelper getInstance(){
		return instance;
	}
	
	public IAJAXCommand getCommand(String commandName){
		CommandName name= CommandName.valueOf(commandName.toUpperCase());
		IAJAXCommand command;
		
		if(null != name){
			command = commands.get(name);
		} else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		
		return command;
	}
}
