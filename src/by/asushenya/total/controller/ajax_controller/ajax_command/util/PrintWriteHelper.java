package by.asushenya.total.controller.ajax_controller.ajax_command.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.controller.ajax_controller.ajax_command.exception.AJAXCommandException;

/**
 * Class help write information at servlet response
 * 
 * @author Artyom Sushenya
 *
 */
public final class PrintWriteHelper {

	private static final Logger log = Logger.getLogger(PrintWriteHelper.class);

	/**
	 * 
	 * @param response
	 *            output source
	 * @param writedMessage
	 *            message written in response
	 * @throws AJAXCommandException
	 */
	public static void printToPrintWriter(HttpServletResponse response, String writedMessage)
			throws AJAXCommandException {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(writedMessage);
		} catch (IOException e) {
			log.error("can't write to PrintWriter", e);
		} finally {
			writer.close();
		}

	}
}
