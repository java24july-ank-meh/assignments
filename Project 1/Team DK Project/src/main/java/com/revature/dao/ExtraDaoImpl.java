package com.revature.dao;

import java.util.Map;

public class ExtraDaoImpl implements ExtraDao {

	@Override
	public Map<String, String> returnLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateUserName(String un) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(String un, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateFullLogin(String un, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePassword(int uID, String npwd) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Integer> returnUserRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> returnReimbursementTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> returnReimbursementStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
