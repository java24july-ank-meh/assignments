package com.revature.ConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		
		String url = "demo.cuwvdmddkxll.us-east-1.rds.amazonaws.com:1521";
		final String uname = "ERS";
		final String password = "ERS";
		
		return DriverManager.getConnection(url, uname, password);
		
	}
	
}
