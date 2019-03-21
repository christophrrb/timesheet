import java.sql.*;

public class UserDatabase {

	private static final String USER_DATABASE_NAME = "users";
	private static Connection userDatabaseConnection;

	public static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			
			userDatabaseConnection = DriverManager.getConnection("jdbc:sqlite:users.db");

			Statement stmt = userDatabaseConnection.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS users(" +
							"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
							"username TEXT NOT NULL," +
							"pw TEXT NOT NULL," +
							"first_name TEXT," +
							"last_name TEXT);";

			stmt.executeUpdate(sql);
			stmt.close();					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createUser(String username, String pw, String firstName, String lastName) {
		try {
			Statement stmt = userDatabaseConnection.createStatement();

			String sql = "INSERT INTO users(username, pw, first_name, last_name)" +
							"VALUES('" + username + "', '" + pw + "', '" + firstName + "', '" + lastName + "');";
			
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet authenticateUser(String username, String pw) {
		try {
			Statement stmt = userDatabaseConnection.createStatement();

			String sql = "SELECT * FROM users WHERE username= '" + username + "' AND pw= '" + pw + "' LIMIT 1;";
			return stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void sendCommand(String sql) {
		try {
			if (userDatabaseConnection != null) {
				Statement stmt = userDatabaseConnection.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			} else {
				System.err.println("The database connection has not been established.");
				throw new Exception();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}