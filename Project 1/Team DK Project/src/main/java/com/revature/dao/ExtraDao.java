package com.revature.dao;

import java.util.Map;

public interface ExtraDao {
	
	public Map<String, String> returnLogin();	//username, password
	public Map<String, Integer> returnUserRoles();	//role, id
	public Map<String, Integer> returnReimbursementTypes();	//type, id
	public Map<String, Integer> returnReimbursementStatus();	//status, id

}
