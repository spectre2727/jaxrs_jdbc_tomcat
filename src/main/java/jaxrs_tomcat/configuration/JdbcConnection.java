package jaxrs_tomcat.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/standard?serverTimezone=UTC";
	private static String username = "root";
	private static String password = "root";
	
	public Connection get() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
