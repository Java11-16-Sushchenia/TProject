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
import by.asushenya.total.bean.Team;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.BookMakerDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.ConnectionManager;

public class BookMakerDAOImpl implements BookMakerDAO{
	
	private static final Logger log = Logger.getLogger(UserDAOImpl.class);

	private static final String getAllTeamsQuerry = "select id, name from team";
	private static final String getAllGamesQuerry = "select id, game_kind, date, (select team.name from team  where team.id = game.team_1) as `team_1`, (select team.name from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true";
	private static final String addNewGameQuerry =  "insert into game (game_kind, team_1, team_2, date, k1,kx,k2) values(?,?,?,?,?,?,?)";
	private static final String getTeamIdByName = "select team.id from team where team.name = '";
	private static final String setNewGameRatesQuerry = "update game set k1 = ?, kx = ?, k2 = ? where game.id = ?";
	private static final String makeGameInvisibleQuerry = "update game set is_visible = false where game.id = ?";
	
	public void addGame(Game game) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try{
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(addNewGameQuerry);	
			
			ps.setString(1, game.getGameKind());
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
	
	public List<Game> getAllGames(String local) throws DAOException {
		
	    Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    List<Game> games = new ArrayList<Game>();
	    
	    try {  
	    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        
	        if(local.equals(RequestParameterName.SESSION_LOCAL_RU)){
	        	rs = st.executeQuery(getAllGamesQuerry);
	        } else if(local.equals(RequestParameterName.SESSION_LOCAL_EN)){
	        	rs = st.executeQuery(getAllGamesQuerry);
	        }
	        
	        while (rs.next()) {
	            Game game = new Game();
	            
	            game.setId(rs.getInt("id"));
	            game.setGameKind(rs.getString("game_kind"));
	            game.setFirstTeam(rs.getString("team_1"));
	            game.setSecondTeam(rs.getString("team_2"));
	            game.setDate(rs.getTimestamp("date"));
	            game.setK1(rs.getDouble("k1"));
	            game.setKx(rs.getDouble("kx"));
	            game.setK2(rs.getDouble("k2"));
	            
	            games.add(game);	            
	        }
	        
	    }catch(SQLException e){	 
	    	log.error(e);
	        
	    }
	    finally {
	    	ConnectionManager.disconnectFromDB(rs, st, con);
	    }
	    return games;
	}
	
	private int getTeamIdByName(String teamName) throws DAOException{
		
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;	    
	 
	    int teamId = 1;
	    try {  		    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        rs = st.executeQuery(getTeamIdByName.concat(teamName).concat("'"));	       
	        
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

	@Override
	public List<Team> getAllTeams() throws DAOException {
	
			Connection con = null;
		    Statement st = null;
		    ResultSet rs = null;
		    
		    List<Team> teams = new ArrayList<Team>();
		    
		    try {  		    	    	  	
		        con = ConnectionManager.getDBTotalizatorConnection();
		        st = con.createStatement();
		        rs = st.executeQuery(getAllTeamsQuerry);
		        
		        while (rs.next()) {
		            Team team = new Team();
		            
		            team.setId(rs.getInt("id"));
		            team.setName(rs.getString("name"));
		            
		            teams.add(team);	            
		        }
		        
		    }catch(SQLException e){	 
		    	log.error(e);		        
		    }
		    finally {
		    	ConnectionManager.disconnectFromDB(rs, st, con);
		    }		
		return teams;
	}

	public void setNewGameRates(int gameId, double k1, double kx, double k2) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try{						
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(setNewGameRatesQuerry);
			
			ps.setDouble(1, k1);
			ps.setDouble(2, kx);
			ps.setDouble(3, k2);
			ps.setInt   (4, gameId);
		
			ps.executeUpdate();
		} catch (SQLException e){
			
			throw new DAOException ("DAOException addEquipment: "+e.getMessage());
			
		} finally{	
			
				ConnectionManager.disconnectFromDB(ps, con);		
		}			
	}

	
	public void makeGameInvisible(int gameId) throws DAOException {
	
		Connection con = null;
		PreparedStatement ps = null;
		
		try{						
			con = ConnectionManager.getDBTotalizatorConnection();
			ps = con.prepareStatement(makeGameInvisibleQuerry);
			
			ps.setInt(1, gameId);
		
			ps.executeUpdate();
		} catch (SQLException e){
			
			throw new DAOException ("DAOException addEquipment: "+e.getMessage());
			
		} finally{				
				ConnectionManager.disconnectFromDB(ps, con);		
		}	
	}
}
