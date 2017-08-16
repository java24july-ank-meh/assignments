package com.revature.team201.reimbursement.users;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.team201.reimbursement.db.ManagerDAOImpl;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class Manager extends User{
	
	public Manager(Integer uId, String username, String firstName, String lastName, String eMail) throws IOException, SQLException {
		super(uId, username, firstName, lastName, eMail, Role.MANAGER);
	}

}
