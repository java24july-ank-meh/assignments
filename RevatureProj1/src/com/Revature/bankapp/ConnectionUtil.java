package com.Revature.bankapp;

import java.sql.*;

public class ConnectionUtil {
	public static Connection getConnection(String user, String pass) throws SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = user;
		String password = pass;
		return DriverManager.getConnection(url, username, password);
		
	}
}
