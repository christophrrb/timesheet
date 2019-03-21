import java.sql.*;

public class DatabaseHelperTest {
	public static void main(String[] args) {
		UserDatabase.connect();

		try {
			ResultSet rs = UserDatabase.authenticateUser("test", "test");
			System.out.printf("Username: %s\nFirst Name: %s\nLast Name: %s\n", rs.getString("username"), rs.getString("first_name"), rs.getString("last_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Spreadsheet(new String[] {"8:00", "12:00", "12:30", "4:00"},
						new String[] {"8:00", "12:00", "12:30", "4:00"},
						new String[] {"8:00", "12:00", "12:30", "4:00"},
						new String[] {"8:00", "12:00", "12:30", "4:00"},
						new String[] {"8:00", "12:00", "12:30", "4:00"});
		
	}
}	