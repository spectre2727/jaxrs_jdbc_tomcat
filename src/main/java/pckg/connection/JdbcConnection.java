package pckg.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/standard?serverTimezone=UTC";
	private static String username = "root";
	private static String password = "root";
	
	public Connection get() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
