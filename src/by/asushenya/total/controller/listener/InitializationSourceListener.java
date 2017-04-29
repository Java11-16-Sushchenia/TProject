package by.asushenya.total.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.CommandHelper;
import by.asushenya.total.controller.CommandName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;

/**
 * Performs actions for initialization and destroy data source.
 * 
 * @author Artyom Sushenya
 *
 */
public class InitializationSourceListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(InitializationSourceListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		CommandHelper helper = CommandHelper.getInstance();
		ICommand command = helper.getCommand(CommandName.INITIALIZATION_SOURCE_COMMAND.toString());
		try {
			command.execute(null, null);
		} catch (CommandException e) {
			log.error("can't initialize source", e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		CommandHelper provider = CommandHelper.getInstance();
		ICommand command = provider.getCommand(CommandName.DESTROY_SOURCE_COMMAND.toString());
		try {
			command.execute(null, null);
		} catch (CommandException e) {
			log.error("can't destroy source", e);
		}

	}
}
