package com.revature.util;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnectionUtil {
	public static Connection getConnection() throws IOException,SQLException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		prop.load(in);
		in.close();
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url,username,password);
	}
	
	public static Properties getProperties() {
		Properties prop = new Properties();
		try (InputStream in = new FileInputStream("connection.properties")){
			prop.load(in);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
