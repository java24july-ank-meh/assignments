package com.doubletrouble.main;

import java.io.IOException;
import java.sql.SQLException;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		ERSDao dao = new ERSDaoImpl();
		User u;
		User b;
		
		u = dao.login("JonSnowden", "password");
		
		//System.out.println(u);
		
		b = dao.viewUserInfo(u.getId());
		System.out.println(b.toString());
		//dao.requestReimbursement(300, "I need more money to live please", u.getId(), 1);
		
		//dao.updateUser(u.getId(), "sally", "pw", "jim", "bob", "email");
		
		//dao.viewStatusOfReimbursements(, status)
		
	}

}