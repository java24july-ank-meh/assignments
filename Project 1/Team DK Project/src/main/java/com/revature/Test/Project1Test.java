package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.utilities.ConnectionUtil;

public class Project1Test {

	@Test
	public final void testConnection() {
		try {
			ConnectionUtil.getConection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("Error in connection");
		}
	}
	
}
