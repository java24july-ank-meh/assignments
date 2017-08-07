package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {

	public static Connection getConection() throws SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "superuser";
		String password = "suPWD!$$";
		
		return DriverManager.getConnection(url, username, password);		
	}
	
	public static Connection getConectionProperties() throws IOException, SQLException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connections.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		return DriverManager.getConnection(url, username, password);		
	}

}
