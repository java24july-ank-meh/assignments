package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Photo;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class MainDaoTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		Photo p = new Photo();
		//		p.setPhotoID();
		//		p.updatePhotoD();
		try {

			ConnectionUtil.getConection();
		} catch (/*IOException |*/  SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			ConnectionUtil.getConectionProperties();
		} catch (IOException |  SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	
		//		
//		UserDaoImpl userD = new UserDaoImpl();
		//		User u = new User(1,"json","password", "Jack","d", "aniels","j@yahpp.com",1);
		//		user.createUser(u);
//		userD.readAllUsers();

		
		ReimbursementDaoImpl i = new ReimbursementDaoImpl();
		System.out.println(i.readReimb(100000));
	}

}
