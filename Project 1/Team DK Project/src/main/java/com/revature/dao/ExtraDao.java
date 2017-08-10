package com.revature.dao;

import java.util.Map;

public interface ExtraDao {
	///**/ in cervice dao
	//login
	public Map<String, String> returnLogin();	//username, password
	/**/public boolean validateUserName(String un);	//true if username in db
	/**/public boolean validatePassword(String un,String pwd);//true if username in db has this password
	/**/public boolean validateFullLogin(String un, String pwd); //true if username in db has this password
	/**/public void changePassword(int uID, String npwd);
	
	//user roles
	public Map<String, Integer> returnUserRoles();	//role, id
	
	//reimbursement types
	public Map<String, Integer> returnReimbursementTypes();	//type, id
	
	//reimbursement status
	public Map<String, Integer> returnReimbursementStatus();	//status, id

}
