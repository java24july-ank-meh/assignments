package com.revbank.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection(String url, String username, String password) throws SQLException {
		url = "jdbc:oracle:thin:@localhost:1521:xe";
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