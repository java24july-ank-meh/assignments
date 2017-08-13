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
	public void test() throws IOException;
	
}
