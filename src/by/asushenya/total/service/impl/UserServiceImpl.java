package by.asushenya.total.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.GamesPage;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.controller.ResponseParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.service.UserService;
import by.asushenya.total.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	private static final Logger log = Logger.getLogger(
											UserServiceImpl.class);
	
	public List<Rate> getAllUserRates(User user) 
							throws ServiceException {
		
		List<Rate> userRates;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {			
			 userRates = userDAO.getAllUserRates(user);

		} catch (DAOException e) {
			log.error("can't get user rates form dao",e);
			throw new ServiceException("can't get user rates",e);
		}
		
		return userRates;
	}


	public GamesPage getGamesPage(int page, 
								  int gamesPerPage,
								  GameKind gameKind,
								  String local)
											  throws ServiceException {

		int noOfRecords = 0;
	
		List<Game> gamesList = null;
		
		if(local == null){
			local = RequestParameterName.SESSION_LOCAL_RU;
		}
		

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try{
			 gamesList = userDAO.getGamesForPage((page-1)*gamesPerPage,
					 						 gamesPerPage,
					 						 gameKind,
					 						 local);

			 noOfRecords = userDAO.getGamesRecordsByGameKindCount(gameKind);
		} catch(DAOException e){
			log.error("can't get games page form dao",e);
			throw new ServiceException("Can't get games from dao",e);
		}

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / gamesPerPage);
		
		GamesPage gamesPage = new GamesPage();
		gamesPage.setGamesList(gamesList);
		gamesPage.setNumberOfPages(noOfPages);
		
		return gamesPage;
	}


	public String makeRate(int gameId, 
						   User user,
						   String choice, 
						   double rateCoefficient, 
						   double rateMoney)
								   	throws ServiceException {
		System.out.println(user.getCash());
		if(user.getCash() < rateMoney){
			JSONObject makeRateError = new JSONObject();
			makeRateError.put(ResponseParameterName.ERROR_TYPE, 
							  ResponseParameterName.MAKE_RATE_ERROR);
			makeRateError.put(ResponseParameterName.ERROR_MSSAGE, 
					  ResponseParameterName.NO_MONEY);
			return makeRateError.toString();
		}
		
		return null;
	}

}
