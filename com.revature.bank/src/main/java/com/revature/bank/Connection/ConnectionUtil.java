package com.revature.bank.Connection;

import java.sql.*;

public class ConnectionUtil {


	public static Connection getConnection() throws SQLException {
		String url = "JDBC:oracle:thin:@localhost:1521:xe";
		final String username = "adminone";
		final String password = "adminpass";
			
		return DriverManager.getConnection(url, username, password);
		
	}
}
