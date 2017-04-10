package by.asushenya.total.controller.ajax_controller.ajax_command.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.controller.ajax_controller.ajax_command.IAJAXCommand;
import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;
import by.asushenya.total.controller.ajax_controller.ajax_command.util.PrintWriteHelper;
import by.asushenya.total.service.BookMakerService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class MakeGameInvisibleAJAXCommand implements IAJAXCommand {

	private static final Logger log = Logger.getLogger(MakeGameInvisibleAJAXCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		HashMap<String, Object> changeGameError = new HashMap<String, Object>();
		int gameId = 0;
		try {
			gameId = Integer.parseInt(request.getParameter(RequestParameterName.GAME_ID));
		} catch (NumberFormatException e) {
			log.error("invalid game id", e);
			changeGameError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_GAME_INVISIBLE_ERROR);
			changeGameError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject jsonInfo = new JSONObject(changeGameError);
			PrintWriteHelper.printToPrintWriter(response, jsonInfo.toString());
			return;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookMakerService bookMakerService = serviceFactory.getBookMakerService();

		try {
			bookMakerService.makeGameInvisible(gameId);
		} catch (ServiceException e) {
			log.error("can't make game invisible", e);
			throw new AJAXCommandException("can't make game invisible", e);
		}

		changeGameError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.SUCCESS);
		PrintWriteHelper.printToPrintWriter(response, new JSONObject(changeGameError).toString());
	}

}
