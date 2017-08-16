package com.revature.team201.reimbursement.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;


public class ConnectionUtil {
	public static Connection getConnection() throws IOException, SQLException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("secrets.properties");
		Properties properties = new Properties();
		properties.load(input);
		
		String url = properties.getProperty("url-aws");
		String user = properties.getProperty("user-aws");
		String password = properties.getProperty("password-aws");
		
		OracleDriver driver = new OracleDriver();
        DriverManager.registerDriver(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
