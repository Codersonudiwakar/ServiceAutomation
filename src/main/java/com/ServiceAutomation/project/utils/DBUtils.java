package com.ServiceAutomation.project.utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs;
	
	   public static String url=ConfigUtils.getProperty("DBurl");
	   public static String username=ConfigUtils.getProperty("DBusername");
	   public static String password=ConfigUtils.getProperty("DBpassword");
	

	
	public static String valueFromDB(String url, String userName, String pwd, String query) throws ClassNotFoundException, SQLException {
		String q = null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@"+url+"",userName,pwd);  
		stmt=con.createStatement(); 
		rs=stmt.executeQuery(query); 
		while(rs.next()) {
			try {
			q=rs.getString(1);
			
			}
			catch(Exception e) {
				try {
				q = Integer.toString(rs.getInt(1));
				
				}
				catch(Exception r) {
				q=Double.toString(rs.getDouble(1));
				
				}
			}
		}
		}
		catch(Exception r) {
			r.printStackTrace();
		}
		finally {
			 	rs.close();
			    stmt.close();
			    con.close();
		}
		return q;
	
	}
	
	
	
	
	
	public static ResultSet DBFetchData(String url,String dbuserName,String dbpassword,String strQuery) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@"+url+"",dbuserName,dbpassword); 
		stmt = con.createStatement(); 
		rs= stmt.executeQuery(strQuery);
		return rs;
	}
	

	
	public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
		
		return DBUtils.DBFetchData(url,username ,password ,query);
        
    }
	


	
//	public Connection dbconnection(String dbServer,String serviceName,String userName,String password) throws ClassNotFoundException, SQLException{
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@"+dbServer+":1521:"+serviceName,userName,password);
//		return con;
//	}
	
	public static String dbValue(String query) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		return DBUtils.valueFromDB(url,username ,password ,query);
	}

}


