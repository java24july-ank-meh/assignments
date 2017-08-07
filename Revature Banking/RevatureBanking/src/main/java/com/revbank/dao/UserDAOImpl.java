package com.revbank.dao;

import java.sql.*;

import com.revbank.util.*;

public class UserDAOImpl implements UserDAO {

	@Override
	public void readAccounts(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer createNewAccount(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(int accNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void depositAccount(int accNum, double amt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdrawAccount(int accNum, double amt) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean accountLogin(String username, String password) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			
			String sql = "{CALL ACCOUNT_LOGIN(?, ?, ?)}";
			cs = conn.prepareCall(sql);
			
			cs.setString(1, username);
			cs.setString(2, password);
			cs.registerOutParameter(3,
                    java.sql.Types.INTEGER);
			
			cs.execute();
			
			
			System.out.println("Login status: "+cs.getInt(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean accountLogout(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
