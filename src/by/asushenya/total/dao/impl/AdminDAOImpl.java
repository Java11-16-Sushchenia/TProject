package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.ConnectionManager;

public class AdminDAOImpl implements AdminDAO{
	
	private static final Logger log = Logger.getLogger(AdminDAOImpl.class);
	
	private final static String getAllUsersQuerry = "select id, login, password, email, role, cash from user";
	private static final String addNewGameQuerry =  "insert into game (game_kind, team_1, team_2, date, k1,kx,k2) values(?,?,?,?,?,?,?)";
	private static final String getTeamIdByNameQuerry = "select team.id from team where team.name = '";
	
	public void addGame(Game game) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try{
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(addNewGameQuerry);	
			
			ps.setString(1, game.getGameKind().toString().toLowerCase());
			ps.setInt(2, getTeamIdByName(game.getFirstTeam()));
			ps.setInt(3, getTeamIdByName(game.getSecondTeam()));
			ps.setTimestamp(4, game.getDate());
			ps.setDouble(5,game.getK1());
			ps.setDouble(6,game.getKx());
			ps.setDouble(7,game.getK2());
		
			ps.executeUpdate();
			System.out.println("game is added");
		} catch (SQLException e){
			throw new DAOException ("DAOException addNewGame: "+e.getMessage());
			
			} finally{				
				ConnectionManager.disconnectFromDB(ps, con);
		}			
	}
	
	private int getTeamIdByName(String teamName) throws DAOException{
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;	    
	 
	    int teamId = 1;
	    try {  		    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        rs = st.executeQuery(getTeamIdByNameQuerry.concat(teamName).concat("'"));	       
	        
	        while (rs.next()) {	            
	            teamId = rs.getInt("id");           
	        }
	        
	    }catch(SQLException e){	 
	    	log.error(e);		        
	    }
	    finally {
	    	ConnectionManager.disconnectFromDB(rs, st, con);
	    }
	return teamId;
	}

	

	public List<User> getAllUsers() throws DAOException{
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    List<User> users = new ArrayList<User>();
	    
	    try {  
	    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        rs = st.executeQuery(getAllUsersQuerry);
	        
	        while (rs.next()) {
	            User user = new User();
	            
	            user.setId(rs.getInt("id"));
	            user.setLogin(rs.getString("login"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(UserRole.valueOf(rs.getString("role").toUpperCase()));
	            user.setCash(rs.getFloat("cash"));	            
	            
	            users.add(user);	            
	        }
	        
	    }catch(SQLException e){	 
	    	//log.error(e);	        
	    }
	    finally {
	    	ConnectionManager.disconnectFromDB(rs, st, con);
	    }
	    return users;
	}

}
