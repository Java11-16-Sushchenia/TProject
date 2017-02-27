package by.asushenya.total.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.asushenya.total.bean.Game;
import by.asushenya.total.bean.User;
import by.asushenya.total.bean.util.UserRole;
import by.asushenya.total.dao.AdminDAO;
import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.util.ConnectionManager;

public class AdminDAOImpl implements AdminDAO{
	
	private final static String getAllUsersQuerry = "select id, login, password, email, role, cash from user";
	
	@Override
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
