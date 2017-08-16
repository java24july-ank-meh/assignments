package com.ers.dao;

import java.awt.image.BufferedImage;
import java.util.List;

import com.ers.main.ERSUser;
import com.ers.main.ReimbursementRequest;

public interface DAO {
	
	public final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public int login(String username, String password);
	public Boolean logout(String username, String password);
	
	
	// Employee methods
	public int submitReimbursementRequest(String username, String password, ReimbursementRequest reimbursementReq);
	
	public int uploadReceiptImage(String username, String password, BufferedImage imgbuf, int rid);
	
	public List<ReimbursementRequest> viewPendingRequests(String username, String password);
	
	public List<ReimbursementRequest> viewResolvedRequests(String username, String password);
	
	public ERSUser viewEmployeeInfo(String username, String password);
	
	public int updateEmployeeInfo(String username, String password, String field, String value);
	
	// Manager methods
	public int changeRequestStatus(String username, String password, int reqId, String status);
	
	public List<ReimbursementRequest> viewAllPendingRequests(String username, String password);
	
	public List<ReimbursementRequest> viewAllResolvedRequests(String username, String password);
	
	public List<ERSUser> viewAllEmployees(String username, String password);
	
	public List<ReimbursementRequest> viewEmployeeRequests(String username, String password, String employeeId);
	
	public BufferedImage viewReceiptImage(String username, String password, int reqId);
	
	

}
