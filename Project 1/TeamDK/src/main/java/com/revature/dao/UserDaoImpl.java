package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> readUsersByRoleID(int rid) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionUtil.getConection()) {
			String sql1 = "Select * From Web_Users Where UR_ID=?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, rid);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				User u = new User();
				String f = rs.getString("FIRST_NAME");
				String m = rs.getString("M_INTIAL");
				String l = rs.getString("Last_Name");
				String e = rs.getString("Email");

				u.setFirstName(f);
				u.setMiddleInitial(m);
				u.setLastName(l);
				u.setEmail(e);
				u.setRoleID(rid);
				users.add(u);
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return users;
	}

	@Override
	public List<User> readAllUsers() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionUtil.getConection()) {
			String sql1 = "Select * From Web_Users";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				User u = new User();
				int uid = rs.getInt("User_ID");
				String f = rs.getString("First_Name");
				String m = rs.getString("M_Intial");
				String l = rs.getString("Last_Name");
				String e = rs.getString("Email");
				int ur = rs.getInt("Ur_ID");

				u.setuID(uid);
				u.setFirstName(f);
				u.setMiddleInitial(m);
				u.setLastName(l);
				u.setEmail(e);
				u.setRoleID(ur);
				System.out.println(users.size()+": "+u.toString());
				users.add(u);
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return users;
	}

	@Override
	public User readUser(int uID) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		User u = new User();
		u.setuID(uID);

		try (Connection conn = ConnectionUtil.getConection()) {
			String sql1 = "Select * From Web_Users Where User_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, uID);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String f = rs.getString("First_Name");
				String m = rs.getString("M_Intial");
				String l = rs.getString("Last_Name");
				String e = rs.getString("Email");
				int ur = rs.getInt("Ur_ID");

				u.setFirstName(f);
				u.setMiddleInitial(m);
				u.setLastName(l);
				u.setEmail(e);
				u.setRoleID(ur);
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return u;
	}

	@Override
	public List<String> readAllUsernames() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		List<String> usernames = new ArrayList<String>();

		try (Connection conn = ConnectionUtil.getConection()) {
			String sql1 = "Select * From Web_Users";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String un = rs.getString("User_Name");
				usernames.add(un);
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return usernames;
	}

	@Override
	public Map<String, String> readAllLoginPairs() {
		Map<String, String> logins = new HashMap<String, String>();

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try(Connection conn = ConnectionUtil.getConection()){
			String sql1 = "Select User_Name, Pass_Word, Ur_ID from Web_Users";

			pStmt1 = conn.prepareStatement(sql1);
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String un = rs.getString("User_Name");
				String pwd = rs.getString("Pass_Word");

				logins.put(un, pwd);
			}

		}catch(SQLException se){
			se.printStackTrace();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return logins;
	}
	//-------------------------------------------------------------------------------
	@Override
	public void createUser(User u) {
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConection()) {

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
	public void updateUser1(User u) {
		// Update BADeletedUser Set M_Name=:Old.M_Name, Address=:Old.Address, City=:Old.City, State1=:Old.State1, Postal_Code=:Old.PostalCode   where MyOld_ID = :Old.MyUser_ID;
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConection()) {

			// storing class fields
			String un = u.getUserName();
			String p = u.getPassword();
			String e = u.getEmail();
			int r = u.getRoleID();
			int uid = u.getuID();

			// declare sql statement + arguments
			String sql1 = "Update Web_Users Set User_Name=?, Pass_Word=?, Email=?, Ur_ID Where User_ID=?";

			// create the statement as part of connection
			pStmt1 = conn.prepareStatement(sql1);

			// insert vars
			pStmt1.setString(1, un);
			pStmt1.setString(2, p);
			pStmt1.setString(3, e);
			pStmt1.setInt(4, r);
			pStmt1.setInt(5, uid);

			// execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("User login updated.");
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

	@Override
	public void updateUser2(User u) {
		// TODO Auto-generated method stub
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConection()) {

			// storing class fields
			String f = u.getFirstName();
			String l = u.getLastName();
			String e = u.getEmail();
			String un = u.getUserName();
			String p = u.getPassword();
			int uid = u.getuID();

			// declare sql statement + arguments
			String sql1 = "Update Web_Users Set First_Name=?, Last_Name=?, email=?, User_name=?, Pass_Word=? Where User_ID=?;";

			// create the statement as part of connection
			pStmt1 = conn.prepareStatement(sql1);

			// insert vars
			pStmt1.setString(1, f);
			pStmt1.setString(2, l);
			pStmt1.setString(3, e);
			pStmt1.setString(4, l);
			pStmt1.setString(5, l);
			pStmt1.setInt(6, uid);
			
			// execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("User info updated");
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
	
	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConection()) {

			// storing class fields
			String un = u.getUserName();
			String p = u.getPassword();
			String e = u.getEmail();
			String f = u.getFirstName();
			String m = u.getMiddleInitial(); 
			String l = u.getLastName();
			int ur_id = u.getRoleID();
			int uid = u.getuID();

			// declare sql statement + arguments
			String sql1 = "Update Web_Users Set User_Name=?, Pass_Word=?, Email=?, First_Name=?, M_Intial=?, Last_Name=?, Ur_ID=? Where User_ID=?;";

			// create the statement as part of connection
			pStmt1 = conn.prepareStatement(sql1);

			// insert vars
			pStmt1.setString(1, un);
			pStmt1.setString(2, p);
			pStmt1.setString(3, e);
			pStmt1.setString(4, f);
			pStmt1.setString(5, m);
			pStmt1.setString(6, l);
			pStmt1.setInt(7, ur_id);
			pStmt1.setInt(8, uid);
			
			// execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("User info updated");
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
	public void deleteUser(int uID) {
		//delete from PERSON where P_ID=5;
		PreparedStatement pStmt1 = null;

		// opening new connection
		try (Connection conn = ConnectionUtil.getConection()) {

			// declare sql statement + arguments
			String sql1 = "Delete from Web_Users where User_ID=?;";

			// create the statement as part of connection
			pStmt1 = conn.prepareStatement(sql1);

			// insert vars
			pStmt1.setInt(1, uID);

			// execute query, get result set
			pStmt1.executeUpdate();

			System.out.println("User Deleted.");
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
	
	
	public String loggingIn(String username, String password) {
		String message = null;
		try(Connection conn = ConnectionUtil.getConection()){
			PreparedStatement pstmt = conn.prepareStatement("Select * FROM WEB_USERS WHERE USER_NAME = ? AND PASS_WORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				message = "Success!";
			}else {
				
				message = "Fail!";
			}
						
		}catch(Exception e) {
			message = "Failed username/password";
			e.printStackTrace();
			
		}
		
		return message;
	}

	public int loginReturnID(String username, String password) {
		int uid = 0;
		try(Connection conn = ConnectionUtil.getConection()){
			PreparedStatement pstmt = conn.prepareStatement("Select * FROM WEB_USERS WHERE USER_NAME = ? AND PASS_WORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				uid = rs.getInt("User_ID");
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	public User loginReturnPartial(String username, String password) {
		User u = new User();
		try(Connection conn = ConnectionUtil.getConection()){
			PreparedStatement pstmt = conn.prepareStatement("Select * FROM WEB_USERS WHERE USER_NAME = ? AND PASS_WORD = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				u.setuID(rs.getInt("User_ID"));
				u.setFirstName(rs.getString("First_Name"));
				u.setLastName(rs.getString("Last_Name"));
				u.setRoleID(rs.getInt("Ur_ID"));
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	
	
}
