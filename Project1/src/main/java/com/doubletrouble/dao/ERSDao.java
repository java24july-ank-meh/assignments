package com.doubletrouble.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.doubletrouble.domain.Reimbursements;
import com.doubletrouble.domain.User;
import com.doubletrouble.util.ConnectionUtil;

public interface ERSDao {
	
	public User login(String username, String password) throws IOException, SQLException;
	
	public void requestReimbursement(double amount, String description, int user_id, int type) throws IOException, SQLException;
	
	public void updateUser(int id, String username, String password, String fName, String lName, String email) throws IOException, SQLException;
	
	public User viewUserInfo(int id) throws IOException, SQLException;
	
	public List<Reimbursements> viewPending(int id) throws IOException, SQLException;
	
	public List<Reimbursements> viewResolved(int id) throws IOException, SQLException;
	
}