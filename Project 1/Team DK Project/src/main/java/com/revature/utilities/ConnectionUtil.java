package com.revature.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

//import oracle.jdbc.OracleDriver;


public class ConnectionUtil {
	public static Connection getConection() throws SQLException{
		String url = "project1-dk.cbavkfkfpppe.us-east-2.rds.amazonaws.com:1521:orcl";
		String username = "dbsystem";
		String password = "oracleSE217";

		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection(url, username, password);		
	}

	public static Connection getConectionProperties() throws IOException, SQLException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");//conn.prop
		prop.load(in);

		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		return DriverManager.getConnection(url, username, password);		
	}
}
