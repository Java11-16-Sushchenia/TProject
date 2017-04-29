package by.asushenya.total.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.GamesPage;
import by.asushenya.total.controller.command.ICommand;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.SessionAttributeName;
import by.asushenya.total.controller.command.CommandException;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;
import by.asushenya.total.service.factory.ServiceFactory;

/**
 * This command put list {@link Game} of some kind at request depending on
 * localization.
 * 
 * @author Artyom Sushenya
 *
 */
public class GetPageWithGamesCommand implements ICommand {

	private static final Logger log = Logger.getLogger(GetPageWithGamesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		int page = 1;
		int recordsPerPage = 5;
		GameKind gameKind = null;

		String local = (String) request.getSession(true).getAttribute(SessionAttributeName.LOCAL);

		GamesPage pageWithGames = new GamesPage();

		if (local == null) {
			local = RequestParameterName.SESSION_LOCAL_RU;
		}

		if (request.getParameter(RequestParameterName.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
		}

		if (request.getParameter(RequestParameterName.GAME_KIND) != null
				&& !request.getParameter(RequestParameterName.GAME_KIND).isEmpty()) {

			String gameKindInString = request.getParameter(RequestParameterName.GAME_KIND);
			gameKind = GameKind.valueOf(gameKindInString.toUpperCase());
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			pageWithGames = userService.getGamesPage(page, recordsPerPage, gameKind, local);
		} catch (ServiceException e) {
			log.error("can't get page with games", e);
			throw new CommandException("can't get page with games", e);
		}

		request.setAttribute(RequestParameterName.GAMES, pageWithGames.getGamesList());
		request.setAttribute(RequestParameterName.NUMBER_OF_PAGES, pageWithGames.getNumberOfGamePages());
		request.setAttribute(RequestParameterName.CURRENT_PAGE, page);

		if (gameKind == null) {
			request.setAttribute(RequestParameterName.GAME_KIND, null);
		} else {
			request.setAttribute(RequestParameterName.GAME_KIND, gameKind.toString());
		}

		return request.getParameter(RequestParameterName.GO_TO_PAGE);
	}

}
