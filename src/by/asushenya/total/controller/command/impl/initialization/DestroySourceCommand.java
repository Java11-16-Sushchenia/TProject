package by.asushenya.total.controller.command.impl.initialization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.service.InitializationSourceService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class DestroySourceCommand implements ICommand{
	private static final Logger log = Logger.getLogger(DestroySourceCommand.class);

	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

			ServiceFactory factory = ServiceFactory.getInstance();
			InitializationSourceService initializationService = factory.getInitializationSourceService();
			 
			try {
				initializationService.destroySource();
				log.info("Source has been destroyed");
			} catch (ServiceException e) {
				log.info("Database has not been destroyed");
				log.error(e);
			}
			return null;
		
	}
	

}
