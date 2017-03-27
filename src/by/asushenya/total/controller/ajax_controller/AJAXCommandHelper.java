package by.asushenya.total.controller.ajax_controller;

import java.util.HashMap;
import java.util.Map;

/*
import by.asushenya.total.service.ajax_command.impl.MakeRateAJAXCommand;
import by.asushenya.total.service.ajax_command.impl.RegistrateUserAJAXCommand;
import by.asushenya.total.service.ajax_command.impl.SetNewGameRatesAJAXCommand;

import by.asushenya.total.service.ajax_command.impl.AddNewGameAJAXCommand;
import by.asushenya.total.service.ajax_command.impl.AuthorizationUserAJAXCommand;
import by.asushenya.total.service.ajax_command.impl.GetTeamsOfSomeGameKindAJAXCommand;
import by.asushenya.total.service.ajax_command.impl.MakeGameInvisibleAJAXCommand;*/

import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.AJAXCommandName;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.GetTeamsByGameKindAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.MakeGameInvisibleAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.MakeRateAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.SetNewGameCoefficientsAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.SignInAJAXCommand;
import by.asushenya.total.controller.command.impl.GetPageWithGamesCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.RegistrationUserAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.impl.AddNewGameAJAXCommand;


public final class AJAXCommandHelper {
	private static final AJAXCommandHelper instance = new AJAXCommandHelper();
	
	private Map<AJAXCommandName,IAJAXCommand> commands = new HashMap<AJAXCommandName,IAJAXCommand>();

	
	public AJAXCommandHelper(){
		/*commands.put(CommandName.MAKE_RATE_COMMAND, new MakeRateAJAXCommand());
		commands.put(CommandName.AUTHORIZATION_USER_COMMAND, new AuthorizationUserAJAXCommand());
		commands.put(CommandName.SET_NEW_GAME_RATES_AJAX_COMMAND, new SetNewGameRatesAJAXCommand());
		commands.put(CommandName.MAKE_GAME_INVISIBLE_AJAX_COMMAND, new MakeGameInvisibleAJAXCommand());
		commands.put(CommandName.ADD_NEW_GAME_AJAX_COMMAND, new AddNewGameAJAXCommand());
		commands.put(CommandName.GET_TEAMS_OF_SOME_GAME_KIND_AJAX_COMMAND, new GetTeamsOfSomeGameKindAJAXCommand());
		commands.put(CommandName.REGISTRATION_USER_AJAX_COMMAND, new RegistrateUserAJAXCommand());*/
		
		commands.put(AJAXCommandName.REGISTRATION_USER_AJAX_COMMAND, new RegistrationUserAJAXCommand());
		commands.put(AJAXCommandName.AUTHORIZATION_SIGN_IN_AJAX_COMMAND, new SignInAJAXCommand());
		commands.put(AJAXCommandName.MAKE_RATE_AJAX_COMMAND, new MakeRateAJAXCommand());
		commands.put(AJAXCommandName.SET_NEW_GAME_COEFFICIENTS_AJAX_COMMAND, new SetNewGameCoefficientsAJAXCommand());
		commands.put(AJAXCommandName.MAKE_GAME_INVISIBLE_AJAX_COMMAND, new MakeGameInvisibleAJAXCommand());
		commands.put(AJAXCommandName.GET_TEAMS_BY_GAME_KIND_AJAX_COMMAND, new GetTeamsByGameKindAJAXCommand());
		commands.put(AJAXCommandName.ADD_NEW_GAME_AJAX_COMMAND, new AddNewGameAJAXCommand());
		
	}
	
	public static AJAXCommandHelper getInstance(){
		return instance;
	}
	
	public IAJAXCommand getCommand(String commandName){
		AJAXCommandName name= AJAXCommandName.valueOf(commandName.toUpperCase());
		IAJAXCommand command;
		
		if(null != name){
			command = commands.get(name);
		} else{
			command = commands.get(AJAXCommandName.NO_SUCH_COMMAND);
		}
		
		return command;
	}
}
