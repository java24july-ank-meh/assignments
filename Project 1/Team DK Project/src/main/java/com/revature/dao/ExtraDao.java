package com.revature.dao;

import java.io.InputStream;
import java.util.Map;

public interface ExtraDao {
	
	//user roles
	public Map<String, Integer> returnUserRoles();	//role, id
	
	//reimbursement types
	public Map<String, Integer> returnReimbursementTypes();	//type, id
	
	//reimbursement status
	public Map<String, Integer> returnReimbursementStatus();	//status, id

	//recipt in reimbursments
	public void addPhoto(int rid,byte[] pdata);
	
	//read reciept
	public void uploadReciept(InputStream is, int rid);
}
