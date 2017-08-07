package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil
{
	public static java.sql.Connection getConnectionFromProperties() throws IOException, SQLException
	{
		Properties properties = new Properties();
		
		InputStream inputStream = new FileInputStream("connection.properties");
		
		properties.load(inputStream);
		
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		return DriverManager.getConnection(url, username, password);
		
		
	}
}
