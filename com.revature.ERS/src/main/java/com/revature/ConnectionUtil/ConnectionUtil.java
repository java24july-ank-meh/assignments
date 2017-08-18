package com.revature.ConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;


public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		
	
		//ConnectionUtil.
		String url = "JDBC:oracle:thin:@localhost:1521:xe";
		final String uname = "ERS";
		final String password = "ERS";
		
		
		
		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection(url,uname,password);
	}
	
}
