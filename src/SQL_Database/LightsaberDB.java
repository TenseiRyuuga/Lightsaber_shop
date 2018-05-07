package SQL_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import constants.Constant;

import com.mysql.cj.Constants;

public class LightsaberDB {

	public static String databaseUrl = "jdbc:mysql://localhost:3306/lightsaberdb?useSSL=false&serverTimezone=UTC";
	
	private static LightsaberDB dirDB;
	private Connection connection;

	public LightsaberDB() {
		this(false);
	}
	
	public LightsaberDB(boolean test) {
		try
		{
			//loading the jdbc driver
			System.out.println("Loading Driver...");
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//get a connection to database
			System.out.println("Getting Connection to database...");
			//We don't have certificates so not using SSL
			connection = DriverManager.getConnection(databaseUrl,"root","");  
		}
		catch(SQLException e)
		{
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to establish a connection with the database");
			System.out.println(e);   
		}
		catch(Exception e)
		{
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to load database driver");
			System.out.println(e);
		}

		if(test) {
			checkAndCreateTestData();
		}
	}

	private void checkAndCreateTestData() {
		boolean createTabel = !checkTestTabel();
		if (createTabel) {
			System.out.println("Test DB not found!");
			System.out.println("Creating test DB");
			createTables();
		}
		else if (!createTabel) {
			System.out.println("Table " + Constant.TABLE_LIGHTSABER + " already exists!");
		}
	}

	
	private boolean checkTestTabel() {
		try {
			System.out.println("Checking Test DB...");
			java.sql.DatabaseMetaData dbMetaData = connection.getMetaData();
			ResultSet rs = dbMetaData.getTables(null, null, Constant.TABLE_LIGHTSABER, null);
			return rs.next();
		} catch (SQLException e) {
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to check TestTabel");
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	private void createTables() {
		try {
			System.out.println("Creating Test DB...");
			Statement statement = connection.createStatement();
			String query = "CREATE TABLE " + Constant.TABLE_CRYSTALS_PROPERTIES + " (planet_of_origin_name varchar(255) , crystal_price int(11), power_usage int(11), PRIMARY KEY(planet_of_origin_name));";
			statement.execute(query);
			statement = connection.createStatement();
			query = "CREATE TABLE " + Constant.TABLE_CRYSTAL + " (crystal_color varchar(255), planet_of_origin_name varchar(255),  PRIMARY KEY(crystal_color), FOREIGN KEY (planet_of_origin_name) REFERENCES crystal_prices(planet_of_origin_name));";
			statement.execute(query);
			statement = connection.createStatement();
			query = "CREATE TABLE " + Constant.TABLE_LIGHTSABER + " (ID int(11) NOT NULL AUTO_INCREMENT, given_ID int(11), name varchar(255), available int, crystal_color varchar(255), PRIMARY KEY(ID), FOREIGN KEY (crystal_color) REFERENCES lightsaber_crystals(crystal_color));";
			statement.execute(query);
		} catch (SQLException e) {
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to create TestTabel");
			System.out.println(e);
		}
	}

	public LightsaberDB getInstance() {
		if(dirDB == null) {
			dirDB = new LightsaberDB();
		} 
		return dirDB;
	}

	public Connection getConnection() {
		return connection;
	}
}
