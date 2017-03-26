package by.asushenya.total.service;

import java.util.List;

import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.UsersPage;
import by.asushenya.total.service.exception.ServiceException;

public interface AdminService{
	//String getAllUsers() throws ServiceException;
	UsersPage getUsersPage(int page,
						   int usersPerPage)
								throws ServiceException;
	List<Team> getTeamsByGameKind(GameKind gameKind, String local)
								throws ServiceException;
}
