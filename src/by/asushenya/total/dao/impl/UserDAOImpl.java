package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.Rate;
import by.asushenya.total.bean.Team;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.GameKind;
import by.asushenya.total.bean.util.RateChoice;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.controller.RequestParameterName;
import by.asushenya.total.dao.UserDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.ConnectionManager;

import org.apache.log4j.Logger;

import com.sun.xml.internal.ws.spi.db.DatabindingException;

public class UserDAOImpl implements UserDAO{
	
	private static final Logger log = Logger.getLogger(UserDAOImpl.class);
	
	
	private static final String insertIntoUser = "insert into user ( login, password,email,role,cash) values(?,?,?,?,?);";
	private static final String findUserByLogin = "select id, login,email, password , role, cash from user where login = '";
	private static final String findUserByEmail = "select id, login,email, password , role, cash from user where email = '";
	private static final String getAllUserRateRuQuerry = "select rate.id, (select name from team where team.id = game.team_1) `team_1`, (select name from team where team.id = game.team_2) `team_2`, rate.date, money, choice, game_coefficient, profit, is_success from rate inner join game on rate.game_id = game.id where user_id = '";
	private static final String getAllUserRateEnQuerry = "select rate.id, (select name_en from team where team.id = game.team_1) `team_1`, (select name_en from team where team.id = game.team_2) `team_2`, rate.date, money, choice, game_coefficient, profit, is_success from rate inner join game on rate.game_id = game.id where user_id = '";
	private static final String getGamesByTypeQuerry = "select id, game_kind, date, (select team.name from team  where team.id = game.team_1) as `team_1`, (select team.name from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true";
	private static final String getGamesByTypeEnQuerry = "select id, game_kind, date, (select team.name_en from team  where team.id = game.team_1) as `team_1`, (select team.name_en from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true";
	private static final String makeRateQuerry = "insert into rate (user_id, game_id, money, choice, game_coefficient) values(?,?,?,?,?)";
	private static final String getAllGamesCountQuerry = "select count(*) `games_count` from game where is_visible = true";
	private static final String getRatesCountQuerry = "select count(*) `rates_count` from rate where user_id = ?"; // where game_rate > now();
	private static final String getAllGamesQuerry = "select id, game_kind, date, (select team.name from team  where team.id = game.team_1) as `team_1`, (select team.name from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true";
	private static final String getAllGamesEnQuerry = "select id, game_kind, date, (select team.name_en from team  where team.id = game.team_1) as `team_1`, (select team.name_en from team where team.id = game.team_2) as `team_2`, k1, kx, k2 from game where is_visible = true";
	
	private static final String getAllTeamsRuQuerry = "select id, name as `name` from team where game_kind = ?";
	private static final String getAllTeamsEnQuerry = "select id, name_en `name` from team where game_kind = ?";
	
	public User findUserByEmail(String email) throws DAOException{
		Connection con = null;
	  Statement st = null;
	  ResultSet rs = null;
		  
		  User user = null;
		  try {  	    	  	
		        con = DriverManager.getConnection(
		        		"jdbc:mysql://127.0.0.1/totalizator","root","1111");
		        
		        st = con.createStatement();	        
		        
		        rs = st.executeQuery(findUserByEmail
		        					.concat(email)
		        					.concat("'"));
		        
		        while (rs.next()) {
		            user = new User();
		        	user.setId(rs.getInt("id"));
		        	user.setLogin(rs.getString("login"));
		        	user.setEmail(rs.getString("email"));
		        	user.setPassword(rs.getString("password"));
		        	user.setRole(UserRole.valueOf(rs.getString("role").
		        											toUpperCase()));
		        	user.setCash(rs.getFloat("cash")); 		        	
		        }
		       } catch(SQLException e){
		    	   log.error("can't find user by email",e);
		    	   throw new DAOException("user finding problem",e);
		       }
		    finally {		       
		        ConnectionManager.disconnectFromDB(rs, st, con);
		    }
		    return user;		
	}
	
	public void registeredNewUser(User user) throws DAOException{
		 Connection con = null;
		 PreparedStatement st = null;
			
			try{
				con = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/totalizator","root","1111");
				
				PreparedStatement ps = con.prepareStatement(insertIntoUser);
				
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, String.valueOf( UserRole.USER));
				ps.setDouble(5, 100.0D);
			
				ps.executeUpdate();			
				
			} catch (SQLException e){		
				log.error("can't register new user",e);
				throw new DAOException ("DAOException registeredNewUser: "
										+e.getMessage());
				
				} finally{
					ConnectionManager.disconnectFromDB(st, con);
			}    
	}
	
	@Override
	public User findUserByLogin(String userLogin) throws DAOException{
		  Connection con = null;
		  Statement st = null;
		  ResultSet rs = null;
		  
		  User user = null;
		  try {  	    	  	
		        con = DriverManager.getConnection(
		        		"jdbc:mysql://127.0.0.1/totalizator","root","1111");
		        st = con.createStatement();	        
		        
		        rs = st.executeQuery(findUserByLogin+userLogin+"'");
		        
		        while (rs.next()) {
		            user = new User();
		        	user.setId(rs.getInt("id"));
		        	user.setLogin(rs.getString("login"));
		        	user.setEmail(rs.getString("email"));
		        	user.setPassword(rs.getString("password"));
		        	user.setRole(UserRole.valueOf(rs.getString("role")
		        										.toUpperCase()) );
		        	user.setCash(rs.getFloat("cash")); 		        	
		        }
	        } catch(SQLException e){
	    	   log.error("can't find user by login",e);
	    	   throw new DAOException("user finding problem",e);
	        }
		    finally {		       
		        ConnectionManager.disconnectFromDB(rs, st, con);
		    }
		    return user;	
	}

	@Override
	public List<Rate> getAllUserRates(User user) throws DAOException {
	
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    List<Rate> rates = new ArrayList<Rate>();
	    
	    try {  	    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        rs = st.executeQuery(user.getId()+"'");
	            	
	        while (rs.next()) {
	            Rate rate = new Rate();
	            
	            rate.setId(rs.getInt("id"));
	            
	            Game game = new Game();
	            game.setFirstTeam(rs.getString("team_1"));
	            game.setSecondTeam(rs.getString("team_2"));	            
	            rate.setGame(game);
	         
	            rate.setDate(rs.getTimestamp("date"));
	            
	            rate.setMoney(rs.getDouble("money"));
	            rate.setChoice(RateChoice.valueOf(rs.getString("choice")));
	            rate.setGameCoefficient(rs.getDouble("game_coefficient"));
	            rate.setProfit(rs.getDouble("profit"));
	            rate.setisSuccess(rs.getBoolean("is_success"));
	            
	            rates.add(rate);	            
	        }
	        
	    }catch(SQLException e){	 
	    	log.error("can't get all user rates",e);
	    	throw new DAOException("can't get all user rates",e);	        
	    }
	    finally {
	    	ConnectionManager.disconnectFromDB(rs, st, con);
	    }
	    return rates;		
	
	}

	public List<Game> getGamesByType(GameKind gameKind) 
										throws DAOException {
	return null;
	/*Depricated*/
		/*Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    List<Game> games = new ArrayList<Game>();
	    StringBuilder executedQuerry = new StringBuilder();
	    	    
	    try {  
	    	    	  	
	        con = ConnectionManager.getDBTotalizatorConnection();
	        st = con.createStatement();
	        
	        rs = st.executeQuery(getGamesByTypeQuerry + "and game_kind = '"+gameKind.toString().toLowerCase()+"'");
	        
	        while (rs.next()) {
	            Game game = new Game();
	            
	            game.setId(rs.getInt("id"));
	            game.setGameKind(GameKind.valueOf(
	            				rs.getString("game_kind").toUpperCase()));
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
	    System.out.println(games.size());
	    return games;*/
	}

	@Override
	public boolean makeRate(Rate rate) throws DAOException {
		
		 Connection con = null;
		 PreparedStatement st = null;
			
			try{
				con = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/totalizator","root","1111");
				
				PreparedStatement ps = con.prepareStatement(makeRateQuerry);
				
				ps.setInt(1, 	rate.getUser().getId());
				ps.setInt(2, 	rate.getGame().getId());
				ps.setDouble(3, rate.getMoney());
				ps.setString(4, rate.getChoice().toString());
				ps.setDouble(5, rate.getGameCoefficient());
			
				ps.executeUpdate();			
				
			} catch (SQLException e){		
				log.error("can't make rate",e);
				throw new DAOException ("DAOException cant make rate: "
										+e.getMessage());				
				} finally{
					ConnectionManager.disconnectFromDB(st, con);
			}    		
		return true;
	}


	public List<Game> getGamesForPage(int offset, 
									  int noOfRecords, 
									  GameKind gameKind, 
									  String local) 
											  throws DAOException {
		
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 StringBuilder getPartOfGamesByType = new StringBuilder();

		 /*!!!!!! слишком много кода на проверку loca !!!!!!!!!!!!!!*/
		 if(local.equals(RequestParameterName.SESSION_LOCAL_RU)){
			 getPartOfGamesByType.append(getAllGamesQuerry);
		 } else if(local.equals(RequestParameterName.SESSION_LOCAL_EN)){
			 getPartOfGamesByType.append(getAllGamesEnQuerry);
		 }		 
		 
		 if(gameKind != null){
			 getPartOfGamesByType.append(" and game_kind = '"
					 					 +gameKind.toString().toLowerCase()					 				
					 					 +"'");
		 }		 
		 
		 getPartOfGamesByType.append(" limit "+ offset + ", " + noOfRecords);
		 
		List<Game> list = new ArrayList<Game>();		
		
		try {
			con = ConnectionManager.getDBTotalizatorConnection();
			st = con.createStatement();
			 rs = st.executeQuery(getPartOfGamesByType.toString());
			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getInt("id"));
	            game.setGameKind(GameKind.valueOf(
	            		rs.getString("game_kind").toUpperCase()));
	            
	            game.setFirstTeam(rs.getString("team_1"));
	            game.setSecondTeam(rs.getString("team_2"));
	            game.setDate(rs.getTimestamp("date"));
	            game.setK1(rs.getDouble("k1"));
	            game.setKx(rs.getDouble("kx"));
	            game.setK2(rs.getDouble("k2"));
	            list.add(game);
			}
			rs.close();
			
			rs = st.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
				noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			log.error("can't get games for page",e);
		}finally {
			ConnectionManager.disconnectFromDB(rs,st ,con);
		}
		return list;
	}

	public int getGamesRecordsByGameKindCount(GameKind gameKind) throws DAOException {
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 
		 int gamesCount =0;
		 
			try {
				con = ConnectionManager.getDBTotalizatorConnection();
				st = con.createStatement();
				
				if(gameKind != null){
					 rs = st.executeQuery(getAllGamesCountQuerry
							 			 +" and game_kind = '"
							 			 +gameKind.toString()
							 			 +"'");
				}else{
					rs = st.executeQuery(getAllGamesCountQuerry);
				}
				
				while (rs.next()) {					
					gamesCount = rs.getInt("games_count");
				}
			} catch (SQLException e) {
				log.error("can't get games count by game kind",e);
				throw new DAOException("can't get games count",e);
				
			}finally {
				ConnectionManager.disconnectFromDB(rs,st ,con);
			}
			return gamesCount;		
	}


	public List<Team> getTeamsByGameKind(GameKind gameKind, String local)
													throws DAOException {
		
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 List<Team> teamsOfSomeGameKind = new ArrayList<Team>();
		 
			try {
				con = ConnectionManager.getDBTotalizatorConnection();
				
				 if(local.equals(RequestParameterName.SESSION_LOCAL_RU)){
					 ps = con.prepareStatement(getAllTeamsRuQuerry);
				 } else if(local.equals(RequestParameterName.SESSION_LOCAL_EN)){
					 ps = con.prepareStatement(getAllTeamsEnQuerry);
				 }
				 
				 ps.setString(1, gameKind.toString().toLowerCase());
				rs = ps.executeQuery();
				
				while (rs.next()) {					
					Team team = new Team();
					
					team.setId(rs.getInt("id"));
					team.setName(rs.getString("name"));
					
					teamsOfSomeGameKind.add(team);
				}
			} catch (SQLException e) {
				log.error("can't get teams by game kind",e);
				throw new DAOException("can't get teams",e);
				
			}finally
			{
				ConnectionManager.disconnectFromDB(rs,ps ,con);
			}
			return teamsOfSomeGameKind;	
	}

	
	public List<Rate> getRatesForPage(User user,
								      int page,
									  int ratesPerPage,
									  String local) 
											  throws DAOException {
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 StringBuilder getPartOfRatesQuerry = new StringBuilder();
		 
		 if(local.equals(RequestParameterName.SESSION_LOCAL_RU)){
			 getPartOfRatesQuerry.append(getAllUserRateRuQuerry);
		 } else if(local.equals(RequestParameterName.SESSION_LOCAL_EN)){
			 getPartOfRatesQuerry.append(getAllUserRateEnQuerry);
		 }	
		 
		 getPartOfRatesQuerry.append(user.getId()).append("'");
		 
		 getPartOfRatesQuerry.append(" limit "+ page + ", " + ratesPerPage);
		 
		List<Rate> list = new ArrayList<Rate>();		
		
		try {
			con = ConnectionManager.getDBTotalizatorConnection();
			st = con.createStatement();
			 rs = st.executeQuery(getPartOfRatesQuerry.toString());
			while (rs.next()) {
				Rate rate = new Rate();
	            
	            rate.setId(rs.getInt("id"));
	            
	            Game game = new Game();
	            game.setFirstTeam(rs.getString("team_1"));
	            game.setSecondTeam(rs.getString("team_2"));	            
	            rate.setGame(game);
	         
	            rate.setDate(rs.getTimestamp("date"));
	            
	            rate.setMoney(rs.getDouble("money"));
	            rate.setChoice(RateChoice.valueOf(rs.getString("choice")));
	            rate.setGameCoefficient(rs.getDouble("game_coefficient"));
	            rate.setProfit(rs.getDouble("profit"));
	            rate.setisSuccess(rs.getBoolean("is_success"));
				
				list.add(rate);
			}
		} catch (SQLException e) {
			log.error("can't get rates for page",e);
		}finally {
			ConnectionManager.disconnectFromDB(rs,st ,con);
		}
		return list;
	}

	
	public int getRatesRecordsCountOfUser(User user) throws DAOException {
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		
		 int ratesCount =0;
		 
			try {
				con = ConnectionManager.getDBTotalizatorConnection();
				
				ps = con.prepareStatement(getRatesCountQuerry);
				ps.setInt(1, user.getId());
				
				rs = ps.executeQuery();
				
				while (rs.next()) {					
					ratesCount = rs.getInt("rates_count");
				}
			} catch (SQLException e) {
				log.error("can't get rates count",e);
				throw new DAOException("can't get rates count",e);				
			}finally {
				ConnectionManager.disconnectFromDB(rs,ps ,con);
			}
			return ratesCount;	
	}
}
