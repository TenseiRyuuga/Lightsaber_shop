package SQL_Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class LightsaberDB_DAO {

	Connection connection;

	protected LightsaberDB_DAO() {
		connection = new LightsaberDB(true).getConnection();
	}

	protected void create(String query) {
		query = "INSERT INTO " + query;
		runQuery(query);
	}

	protected ResultSet read(String query) {
		query = "SELECT " + query;
		return runQuery(query);
	}

	protected void update(String query) {
		query = "UPDATE " + query;
		runQuery(query);
	}

	protected void delete(String query) {
		query = "DELETE FROM " + query;
		runQuery(query);
	}

//	private Statement getStatement() {
//		try {
//			return connection.createStatement();
//		} catch (SQLException e) {
//			//TODO find a better way to inform the user of this error
//			System.out.println("Exception: Failed to create statement");
//			System.out.println(e);
//		}
//		return null;
//	}

	private ResultSet runQuery(String query) {
		try {
			if(query.startsWith("SELECT ")) {
				return connection.createStatement().executeQuery(query);
			}
			else {
				connection.createStatement().executeUpdate(query);
			}
		} catch (SQLSyntaxErrorException e) {
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to insert because the syntax of the query: \"" + query + "\" is incorrect");
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to insert because the the query: \"" + query + "\" violates a constraint");
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			//TODO find a better way to inform the user of this error
			System.out.println("Exception: Failed to insert");
			System.out.println(e);
		}
		return null;
	}

}