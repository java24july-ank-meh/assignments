package com.revature.dao;

import java.util.ArrayList;

import com.revature.domain.*;
import com.revature.exception.*;

public interface EmployeeDAO {
	public User employeeLogin(String username, String password) throws InvalidLoginException;
	public void updateEmployee(User u);
	public void submitReimbursement();
	ArrayList<Reimbursement> viewReimbursements(User u, int type);
	
	
}
