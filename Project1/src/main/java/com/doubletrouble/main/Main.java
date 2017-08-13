package com.doubletrouble.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.doubletrouble.dao.CHERSDao;
import com.doubletrouble.dao.CHERSDaoImpl;
import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.*;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		ERSDao dao = new ERSDaoImpl();
		CHERSDao dao1 = new CHERSDaoImpl();
		User u;
		
		List<Reimbursements> yay;
		//dao1.test();		
//		u = dao.login("BOBBY", "LIGHT");
		
		
		
		//yay = dao1.viewPending();
		yay = dao1.viewEmpReimbRequests(1);
	 System.out.println(yay.toString()); 	
	}

}