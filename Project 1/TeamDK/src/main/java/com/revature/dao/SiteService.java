package com.revature.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import com.revature.domain.User;

public class SiteService {
	ExtraDao eD;
	UserDao uD;
	ReimbursementDao rD;

	public SiteService() {
		eD = new ExtraDaoImpl();
		uD = new UserDaoImpl();
		rD = new ReimbursementDaoImpl();
	}
	/*
	 * /ExtraDao service methods
	 * 
	 * /
	 **/ // true if username in db has this password
	

	public Map<String, String> returnLogins() {// username, password
		Map<String, String> logins = uD.readAllLoginPairs();
		return logins;
	}

	// if username is in db or correct
	public boolean validateUserName(String un) {
		boolean valid = false;
		Map<String, String> logins = uD.readAllLoginPairs();
		
		valid = logins.containsKey(un);

		return valid;
	}

	// opposite of above: checking fro uniqueness
	public boolean uniqueUsername(String un) {
		
		if (validateUserName(un)) {
			return false;
		} else {
			return true;
		}
		
	}

	public boolean validatePassword(String s, String p) {
		boolean valid = false;
		Map<String, String> logins = uD.readAllLoginPairs();
		String password = logins.get(s);

		if (password.equals(p)) {
			valid = true;
		}

		return valid;
	}

	public boolean validateFullLogin(String un, String pwd) {
		boolean validating = false;
	
		validating = validateUserName(un);
		if (validating) {
			validating = validatePassword(un, pwd);
			if (validating) {
				return true;
			}
		}

		return false;
	}
	
	public void changePassword(int uID, String pwd) {
		User u = new User();
		u.setuID(uID);
		u.setPassword(pwd);
		uD.updateUser2(u);
	}
		//---------------------------------------------------------
		// //user roles
		// public Map<String, Integer> returnUserRoles(); //role, id
	//-----------------------------------------------------------
		// //reimbursement types
		// public Map<String, Integer> returnReimbursementTypes(); //type, id
	//-----------------------------------------------------------
		// //reimbursement status
		// public Map<String, Integer> returnReimbursementStatus(); //status, id

}
