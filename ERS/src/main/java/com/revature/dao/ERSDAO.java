package com.revature.dao;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.revature.domain.*;
import com.revature.exception.*;

public interface ERSDAO {
	public User empLogin(String username, String password) throws InvalidLoginException;
	public boolean updateEmp(User u);
	public boolean submitReimb(Reimbursement rem, InputStream fileContent);
	Reimbursement[] viewUserReimb(User u, int type);
	//manager methods
	public Reimbursement[] viewAllReimb(int type);
	public ArrayList<User> viewAllEmps();
	public User viewEmp(int uid);
	public boolean updateReimb(Reimbursement reimb);
	
}
