package com.revature.alvarado.alejandro.bank.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	
	public static Connection getConnection() throws SQLException, IOException {
		
		Properties prop = new Properties();
		InputStream in = new FileInputStream("resources/application.properties");
		prop.load(in);
	
		String url = prop.getProperty("url-dev");
		String username = prop.getProperty("username-dev");
		String password = prop.getProperty("password-dev");

		return DriverManager.getConnection(url, username, password);

	}
	
}
