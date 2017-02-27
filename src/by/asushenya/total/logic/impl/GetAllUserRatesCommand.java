package by.asushenya.total.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.User;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;
import by.asushenya.total.logic.CommandException;
import by.asushenya.total.logic.ICommand;

public class GetAllUserRatesCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		User user = (User) request.getSession().getAttribute("user");
		List<Rate> rates = null;
		
		System.out.println("Получение ставок пользователя"+user.getLogin());
		
		try {			
			 rates = DAOFactory.getInstance().getUserDAO().getAllUserRates(user);
			 System.out.println(rates.get(0).getGameCoefficient());
			 System.out.println("rates count" + rates.size());
			 request.setAttribute("rates", rates);	
			 return "userPage";
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("returned userPage");
		return "userPage";
	}
	
	

}
