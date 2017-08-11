package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class Project1Test {

//	@Test
//	public final void testConnection() throws IOException, SQLException {
//		ConnectionUtil.getConectionProperties();
//	}
	
	@Test 
	public final void testuserDAOimpl() {
		UserDaoImpl user = new UserDaoImpl();
		User u = new User(1,"json","password", "Jack","d", "aniels","j@yahpp.com",1);
		user.createUser(u);
		
	}
}
