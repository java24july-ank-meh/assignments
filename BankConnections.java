package Main;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BankConnections {

	public static Connection getconnection() throws Exception {
		Properties mProperties = new Properties();

		// We want to read the values in the bank account file
		InputStream in = new FileInputStream("bankaccount.properties");

		mProperties.load(in);

		String url = mProperties.getProperty("url");
		String username = mProperties.getProperty("username");
		String password = mProperties.getProperty("password");
		return DriverManager.getConnection(url, username, password);

	}
}
