package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

public class ConnectionUtil {
	public static Connection getConnection() throws IOException, SQLException {
		/*Properties prop = new Properties();
		prop.load( new FileInputStream("src/main/resources/connection.pro	perties"));
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");*/
		OracleDriver driver = new OracleDriver();
        DriverManager.registerDriver(driver);
		//return DriverManager.getConnection(url, user, password);
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ers", "erspass");
		
	}
}
