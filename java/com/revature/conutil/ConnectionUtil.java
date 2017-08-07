package com.revature.conutil;



import java.sql.*;


public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		String username = "bankapp";

		String password = "numbers";

		return DriverManager.getConnection(url, username, password);

	}
}