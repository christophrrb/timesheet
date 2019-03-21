import java.sql.*;

public class SpreadsheetDatabase {

	private static Connection c = null;
	private static final String TIME_DELIMITER = ",,,,";

	public static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
	
			c = DriverManager.getConnection("jdbc:sqlite:spreadsheets.db");

			String sql = "CREATE TABLE IF NOT EXISTS spreadsheets(" +
							"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
							"user_id INT NOT NULL," +
							"username TEXT NOT NULL," +
							"monday TEXT NOT NULL," +
							"tuesday TEXT NOT NULL," +
							"wednesday TEXT NOT NULL," +
							"thursday TEXT NOT NULL," +
							"friday TEXT NOT NULL," +
							"current TEXT NOT NULL DEFAULT 'Y'," +
							"FOREIGN KEY (user_id) REFERENCES users(id)," +
							"FOREIGN KEY (username) REFERENCES users(username));";

			c.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void storeSpreadsheet(int userID, String username, String[] mondayValues, String[] tuesdayValues, String[] wednesdayValues, String[] thursdayValues, String[] fridayValues) {
		String monday, tuesday, wednesday, thursday, friday;
		monday = tuesday = wednesday = thursday = friday = "";

		for (int i = 0; i < mondayValues.length; i++) {
			if (i == (mondayValues.length - 1))
				monday += mondayValues[i];
			else
				monday += mondayValues[i] + TIME_DELIMITER;
		}

		for (int i = 0; i < tuesdayValues.length; i++) {
			if (i == (tuesdayValues.length - 1))
				tuesday += tuesdayValues[i];
			else
				tuesday += tuesdayValues[i] + TIME_DELIMITER;
		}

		for (int i = 0; i < wednesdayValues.length; i++) {
			if (i == (wednesdayValues.length - 1))
				wednesday += wednesdayValues[i];
			else
				wednesday += wednesdayValues[i] + TIME_DELIMITER;
		}

		for (int i = 0; i < thursdayValues.length; i++) {
			if (i == (thursdayValues.length - 1))
				thursday += thursdayValues[i];
			else
				thursday += thursdayValues[i] + TIME_DELIMITER;
		}

		for (int i = 0; i < fridayValues.length; i++) {
			if (i == (fridayValues.length - 1))
				friday += fridayValues[i];
			else
				friday += fridayValues[i] + TIME_DELIMITER;
		}

		String sql = "INSERT INTO spreadsheets(user_id, username, monday, tuesday, wednesday, thursday, friday)" +
						"VALUES(" + userID + ", '" + username  + "', '" + monday + "', '" + tuesday + "', '" + wednesday + "', '" + thursday + "', '" + friday +"');";

		try {
			c.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[][] restoreSpreadsheet(int userID) {
		String sql = "SELECT monday, tuesday, wednesday, thursday, friday FROM spreadsheets WHERE user_id=" + userID + " ORDER BY id DESC;";

		try {
			ResultSet rs = c.createStatement().executeQuery(sql);

			String[][] returnArray = new String[5][4];

			System.out.println("\n\nStart");
			System.out.println(rs.getString("monday"));
			System.out.println("Start\n\n");
			
			returnArray[0] = rs.getString("monday").split(TIME_DELIMITER);
			returnArray[1] = rs.getString("tuesday").split(TIME_DELIMITER);
			returnArray[2] = rs.getString("wednesday").split(TIME_DELIMITER);
			returnArray[3] = rs.getString("thursday").split(TIME_DELIMITER);
			returnArray[4] = rs.getString("friday").split(TIME_DELIMITER);

			return returnArray;							
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}