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
import by.asushenya.total.service.AdminService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

public class BlockUserAJAXCommand implements IAJAXCommand {
	private static final Logger log = Logger.getLogger(BlockUserAJAXCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws AJAXCommandException {

		int blockingUserId = 0;
		try{
			blockingUserId = Integer.parseInt(request.getParameter(RequestParameterName.USER));
		} catch(NumberFormatException e){
			log.error("bad blocking user id",e);
			HashMap<String, Object> jsonInfo = new HashMap<String, Object>();
			jsonInfo.put(ResponseParameterName.ERROR_TYPE, ResponseParameterName.MAKE_RATE_ERROR);
			jsonInfo.put(ResponseParameterName.ERROR_MSSAGE, ResponseParameterName.INVALID_ID);
			JSONObject makeRateError = new JSONObject(jsonInfo);
			PrintWriteHelper.printToPrintWriter(response, makeRateError.toString());
			return;			
		}
		

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		String serviceResponse = null;

		try {
			serviceResponse = adminService.blockUser(blockingUserId);
		} catch (ServiceException e) {
			log.error("can't block user", e);
			throw new AJAXCommandException("can't block user", e);
		}

		PrintWriteHelper.printToPrintWriter(response, serviceResponse);
	}

}
