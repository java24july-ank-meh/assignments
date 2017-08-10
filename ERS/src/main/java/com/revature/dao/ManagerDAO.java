package com.revature.dao;

import java.util.ArrayList;

import com.revature.domain.*;

public interface ManagerDAO {
	//
	public User managerLogin();
	public ArrayList<Reimbursement> viewPending();
	public ArrayList<Reimbursement> viewResolved();
	public ArrayList<User> viewAllEmployees();
	public User viewEmployee();
	public void updateReimbursement();
	
}
