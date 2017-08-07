package com.revature.ConnectionB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectingBank {
	public static Connection getConnectionProp() throws IOException, SQLException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("properties");
		prop.load(in);
		
		//parsing
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, username, password);
	}
}
