package bank;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class connectionUtil 
{
	public static Connection getConnProp() throws IOException, SQLException
	{
		Properties p = new Properties();
		InputStream in = new FileInputStream("connection.properties");
		
		p.load(in);
		
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		return DriverManager.getConnection(url,  username,  password);
	}
}

