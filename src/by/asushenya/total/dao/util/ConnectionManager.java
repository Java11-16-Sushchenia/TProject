package by.asushenya.total.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.asushenya.total.dao.exception.DAOException;
import by.asushenya.total.dao.impl.UserDAOImpl;

public class ConnectionManager {

	private static final Logger log = Logger.getLogger(ConnectionManager.class);
	
	static {
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e){
			
			log.error("Driver is not found",e);
		}		
	}
	
	public static Connection getDBTotalizatorConnection() throws SQLException{
				
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/totalizator","root","1111");
			
			return con;	
	}
	
	public static void disconnectFromDB(ResultSet rs, Statement st, Connection con)throws DAOException{
		   
		 if (rs != null){
	        	try { 
	        		rs.close(); 
	        	} catch (SQLException e){
	        		log.error("result set close error",e);
	        	}
	        }
		 
	        if (st != null){
	        	try { 
	        		st.close(); 
	        	} catch (SQLException e) {
	        		log.error("statement close error",e);
	        	}
	        }
	        
	        if (con != null){
	        	try { 
	        		con.close(); 
	        	} catch (SQLException e) {
	        		log.error("connection close error",e);
	        	}
	        }
	}
	
	public static void disconnectFromDB(PreparedStatement st, Connection con)throws DAOException{

	        if (st != null){
	        	try { 
	        		st.close(); 
	        	} catch (SQLException e) {
	        		log.error("statement close error",e);
	        	}
	        }
	        
	        if (con != null){
	        	try { 
	        		con.close(); 
	        	} catch (SQLException e) {
	        		log.error("connection close error",e);
	        	}
	        }
	}
	
}
