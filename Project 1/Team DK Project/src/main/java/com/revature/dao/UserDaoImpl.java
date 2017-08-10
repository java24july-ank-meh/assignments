package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getOnlyEmps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getOnlyMans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User readUser(int uID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> readAllUsernames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> readAllLoginPairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(User u) {
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConectionProperties()) {

			// storing class fields
			String p = u.getPassword();
			String f = u.getFirstName();
			String m = u.getMiddleInitial(); 
			String l = u.getLastName();
			String e = u.getEmail();
			int ur_id = u.getRoleID();

			// declare sql statement + arguments
			String sql1 = "Insert into Web_Users (Pass_Word, First_Name, M_Intial, Last_Name, Email, Ur_ID) values ( ?, ?, ?, ?, ?, ?);";

			// create the statement as part of connection
			pStmt1 = conn.prepareStatement(sql1);

			// insert vars
			pStmt1.setString(1, p);
			pStmt1.setString(2, f);
			pStmt1.setString(3, m);
			pStmt1.setString(4, l);
			pStmt1.setString(5, e);
			pStmt1.setInt(6, ur_id);

			// execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("Login User created.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
	//---------------------------------------------------------------------------------------------------
	@Override
	public void updateUserL(User u) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void updateUserI(User u) {
		// TODO Auto-generated method stub

	}
//---------------------------------------------------------------------------------------------------

	@Override
	public void deleteUser(int uID) {
		// TODO Auto-generated method stub

	}

}
