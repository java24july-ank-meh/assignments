package com.revature.dao;

import java.util.ArrayList;

import com.revature.domain.*;
import com.revature.exception.*;

public interface ERSDAO {
	public User employeeLogin(String username, String password) throws InvalidLoginException;
	public boolean updateEmployee(User u);
	public boolean submitReimbursement(Reimbursement re);
	ArrayList<Reimbursement> viewUserReimb(User u, int type);
	//manager methods
	public ArrayList<Reimbursement> viewAllReimb(int type);
	public ArrayList<User> viewAllEmployees();
	public User viewEmployee(int uid);
	public void updateReimbursement();
	
}
