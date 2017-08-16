package com.doubletrouble.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.doubletrouble.dao.CHERSDao;
import com.doubletrouble.dao.CHERSDaoImpl;
import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.Reimbursements;
import com.doubletrouble.domain.User;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		ERSDao dao = new ERSDaoImpl();
		List<Reimbursements> rbs = new ArrayList<Reimbursements>();
		List<User> users = new ArrayList<User>();
		User u;
		User b;
		
		u = dao.login("user", "password");
		rbs =	dao.viewPending(u.getId());
		for(Reimbursements r:rbs)
			System.out.println(r.toString());
		 CHERSDao CHERSdao = new CHERSDaoImpl();
		 /*
		users =CHERSdao.viewAllEmps();
		for(User usr:users)
			System.out.println(usr.toString());
		*/
		
		//System.out.println(u);
		
		//System.out.println(u.toString());
		//dao.requestReimbursement(300, "I need more money to live please", u.getId(), 1);
		
		//dao.updateUser(u.getId(), "sally", "pw", "jim", "bob", "email");
		
		//dao.viewStatusOfReimbursements(, status)
		
	}

}