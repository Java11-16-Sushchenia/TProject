package by.asushenya.total.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Team;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;

@WebServlet("/GetAllTeamsServlet")
public class GetAllTeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(GetAllGamesServlet.class);
	
    public GetAllTeamsServlet() {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Team> teams;
		
		try{
			teams = DAOFactory.getInstance().getBookMakerDAO().getAllTeams();
			request.setAttribute("teams", teams);
			System.out.println(teams.size());
		} catch(DAOException e){
			request.setAttribute("error", "Geting all teams error");
		}
		request.getRequestDispatcher("AddNewGamePage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
