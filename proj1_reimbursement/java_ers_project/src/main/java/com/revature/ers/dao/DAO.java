package com.revature.ers.dao;

import java.io.*;
import java.util.*;

import com.revature.ers.beans.*;

public interface DAO {
	public User verifyUser(String username, String password);
	public User getUser(String username);
	public boolean updateUser(User user);
	public boolean denyRequest(int req_id, int reolver_id);
	public boolean approveRequest(int req_id, int resolver_id);
	public List<Request> getPendingRequests(int employee_id);
	public List<Request> getResolvedRequests(int employee_id);
	public List<Request> getPendingRequests();
	public List<Request> getResolvedRequests();
	public List<User> getEmployees();
	public boolean createUser(String username, String password, String firstname, 
			String lastname, String email, UserRole role);
	public boolean createRequest(double amount,String description, 
			InputStream receipt_image, int user_id, RequestType type);
	public void getReceipt(int receipt_id, OutputStream out);
	public void getReceipt(int user_id, int receipt_id, OutputStream out);
}
