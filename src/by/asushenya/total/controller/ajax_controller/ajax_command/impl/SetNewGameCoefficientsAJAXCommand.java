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

/**
 * This command extract {@link Game} coefficients from ajax request and change
 * game according with coefficients
 * 
 * @author Artyom Asushenya
 *
 */
public class SetNewGameCoefficientsAJAXCommand implements IAJAXCommand {

	private static final Logger log = Logger.getLogger(SetNewGameCoefficientsAJAXCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		int gameId = 0;
		double k1 = 0;
		double kx = 0;
		double k2 = 0;
		HashMap<String, Object> setCoefficientsError = new HashMap<String, Object>();
		try {
			gameId = Integer.parseInt(request.getParameter(RequestParameterName.GAME_ID));
		} catch (NullPointerException e) {
			log.error("invalid game id", e);

			setCoefficientsError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.CHANGE_GAME_ERROR);
			setCoefficientsError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);

			JSONObject changeGameError = new JSONObject(setCoefficientsError);
			PrintWriteHelper.printToPrintWriter(response, changeGameError.toString());
			return;
		}

		try {
			k1 = Double.parseDouble(request.getParameter(RequestParameterName.K1));
			kx = Double.parseDouble(request.getParameter(RequestParameterName.KX));
			k2 = Double.parseDouble(request.getParameter(RequestParameterName.K2));
		} catch (NullPointerException e) {
			log.error("new game coefficients is empty", e);
			setCoefficientsError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.CHANGE_GAME_ERROR);
			setCoefficientsError.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_СOEFFICIENT);
			JSONObject changeGameError = new JSONObject(setCoefficientsError);
			PrintWriteHelper.printToPrintWriter(response, changeGameError.toString());
			return;
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookMakerService bookMakerService = serviceFactory.getBookMakerService();

		try {
			bookMakerService.setNewGameCoefficients(gameId, k1, kx, k2);
		} catch (ServiceException e) {
			log.error("can't set new game coefficients at command", e);
			throw new AJAXCommandException("can't set game coefficients", e);
		}

		setCoefficientsError.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.SUCCESS);
		JSONObject changeGameError = new JSONObject(setCoefficientsError);
		PrintWriteHelper.printToPrintWriter(response, changeGameError.toString());
	}

}
