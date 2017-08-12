package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.dao.ExtraDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class Project1Test {

//	@Test
//	public final void testConnection() throws IOException, SQLException {
//		ConnectionUtil.getConectionProperties();
//	}
//	
//	@Test 
//	public final void testuserDAOimpl() {
//		UserDaoImpl user = new UserDaoImpl();
//		User u = new User(1,"json","password", "Jack","d", "aniels","j@yahpp.com",1);
//		user.createUser(u);
//		
//	}
	
//	@Test
//	public final void getLogins() {
//		
//		UserDaoImpl u = new UserDaoImpl();
//		
//		System.out.println(u.readUsersByRoleID(1));
//		System.out.println(u.readAllUsers());
//		System.out.println(u.readAllUsernames());
//		System.out.println(u.readAllLoginPairs());
//		System.out.println(u.readUser(1));
//		System.out.println(u.readAllUsers());
//
//	}
	
	@Test
	public final void testRiembursementDao() {
		ReimbursementDaoImpl rb = new ReimbursementDaoImpl();
		
//		System.out.println(rb.readAllReimb());
//		System.out.println(rb.readReimb(1));
//		rb.deleteReimb(100001);
//		System.out.println();
//		System.out.println();
		
		
	}
	
	@Test
	public final void testExtraImp() {
		ExtraDaoImpl d = new ExtraDaoImpl();
		System.out.println(d.returnReimbursementStatus());
		
	}
}
