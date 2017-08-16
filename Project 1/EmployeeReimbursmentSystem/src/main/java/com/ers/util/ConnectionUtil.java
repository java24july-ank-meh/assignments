package com.ers.util;

import java.sql.*;
import java.io.*;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "ers";
		String password = "ers";
		return DriverManager.getConnection(url, username, password);
	}
	
	public static Connection getConnection(String url, String username, String password) 
			throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, username, password);
	}
	
	public static Connection getConnectionProp() throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		//System.out.println(url);
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, user, password);
	}
}
