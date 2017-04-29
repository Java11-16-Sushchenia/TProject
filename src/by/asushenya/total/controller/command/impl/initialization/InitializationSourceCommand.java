package by.asushenya.total.controller.command.impl.initialization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.InitializationSourceService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command initialize data source when servlet context is initialized.
 * 
 * @author Artyom Sushenya
 *
 */
public class InitializationSourceCommand implements ICommand {
	private static final Logger log = Logger.getLogger(InitializationSourceCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationSourceService initializationService = factory.getInitializationSourceService();

		try {
			initializationService.initSource();
			log.info("Database has been initialized");
		} catch (ServiceException e) {
			log.info("Database has not been initialized");
			log.error(e);
		}
		return null;
	}

}
