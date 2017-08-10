package com.revature.dao;

import java.util.Map;

public interface ExtraDao {
	///**/ in cervice dao
	//login
	public Map<String, String> returnLogins();	//username, password
	
	//user roles
	public Map<String, Integer> returnUserRoles();	//role, id
	
	//reimbursement types
	public Map<String, Integer> returnReimbursementTypes();	//type, id
	
	//reimbursement status
	public Map<String, Integer> returnReimbursementStatus();	//status, id

}
