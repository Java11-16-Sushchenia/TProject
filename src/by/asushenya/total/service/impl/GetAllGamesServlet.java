package by.asushenya.total.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Team;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.factory.DAOFactory;


@WebServlet("/GetAllGamesServlet")
public class GetAllGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(GetAllGamesServlet.class);

    public GetAllGamesServlet() {
        super();        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PropertyConfigurator.configure("D:\\learn\\Java\\EclipseWorkspase\\TProject\\WebContent\\WEB-INF\\properties\\log4j.properties");
		
		    List<Game> games;
		    
			try {
				games = DAOFactory.getInstance().getBookMakerDAO().getAllGames();				
		        request.setAttribute("games", games);    		        
		        
		    } catch (DAOException e) {
		        request.setAttribute("error", "Retrieving rows failed.");
		        log.error("getting all games error", e);
		    }		 
			
		    request.getRequestDispatcher("gamesPage.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
