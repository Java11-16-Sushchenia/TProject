package by.asushenya.total.service;

import java.sql.Timestamp;
import java.util.List;


import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.service.exception.ServiceException;

public interface AdminService {
	UsersPage getUsersPage(int page, int usersPerPage) throws ServiceException;

	List<Team> getTeamsByGameKind(GameKind gameKind, String local) throws ServiceException;

	String addNewGame(GameKind gameKind, String firstTeamName, String secondTeamName, Timestamp gameDate, double k1,
			double kx, double k2, String local) throws ServiceException;

	String blockUser(int userId) throws ServiceException;

	String unblockUser(int userId) throws ServiceException;
}
