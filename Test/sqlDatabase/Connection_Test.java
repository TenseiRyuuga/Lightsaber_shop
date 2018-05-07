package sqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import SQL_Database.LightsaberDB;

public class Connection_Test {
	
	public static void main(String[] args) {
	       try
	       {
	           //loading the jdbc driver
	    	   System.out.println("Loading Driver...");
	            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	           //get a connection to database
	            System.out.println("Getting Connection to database...");
	           Connection myConn=DriverManager.getConnection(LightsaberDB.databaseUrl,"root","");     
	       }
	       catch(SQLException e)
	       {
	           System.out.println(e);   
	       }
	       catch(Exception e)
	       {
	           System.out.println(e);
	       }
	    }
}