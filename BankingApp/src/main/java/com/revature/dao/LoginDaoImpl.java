package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.User;
import com.revature.util.ConnectionUtility;

public class LoginDaoImpl {

	UserDao ud;
	/*
	 * Works with and for logins
	 */

	public LoginDaoImpl() {
		ud = new UserDaoImpl();
	}

	public void createLogin(User u) {

		if (u != null) {
			PreparedStatement pStmt1 = null;

			// opening new connection
			try (Connection conn = ConnectionUtility.getConectionProperties()) {

				// storing class fields
				String un = u.getUsername();
				String p = u.getPassword();
				String f = u.getfName();
				String l = u.getlName();

				// declare sql statement + arguments
				String sql1 = "Insert Into BALogin(User_Name, Pass_word, F_Name, L_Name) Values(?,?,?,?)";

				// create the statement as part of connection
				pStmt1 = conn.prepareStatement(sql1);

				// insert vars
				pStmt1.setString(1, un);
				pStmt1.setString(2, p);
				pStmt1.setString(3, f);
				pStmt1.setString(4, l);

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

	}
	//-----------------------------------------------------------------
	public User readLogin(int id) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		User u = new User();

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin Where User_ID= ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);
			
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				
				String un = rs.getString("User_Name");
				String p = rs.getString("Pass_word");
				String f = rs.getString("F_Name");
				String l = rs.getString("L_Name");
				
//				System.out.println("User id: " + id);
//				System.out.print("\t User Name: " + un);
//				System.out.print("\t Password: " + p);
//				System.out.print("\t First name: " + f);
//				System.out.print("\t Last name: " + l+"\n");
				
				u.setUserID(id);
				u.setUsername(un);
				u.setPassword(p);
				u.setfName(f);
				u.setlName(l);
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
	
	public User readLoginByUN(String s) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		User u = new User();

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin Where User_Name= ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setString(1,s);
			
			rs = pStmt1.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("User_ID");
				String p = rs.getString("Pass_word");
				String f = rs.getString("F_Name");
				String l = rs.getString("L_Name");
				
//				System.out.println("User id: " + id);
//				System.out.print("\t User Name: " + s);
//				System.out.print("\t Password: " + p);
//				System.out.print("\t First name: " + f);
//				System.out.print("\t Last name: " + l+"\n");
				
				u.setUserID(id);
				u.setUsername(s);
				u.setPassword(p);
				u.setfName(f);
				u.setlName(l);
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
	
	
	public List<Integer>readAllUID(){
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Integer> theList = new ArrayList<Integer>();

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				User u = new User();
				
				int uid = rs.getInt("User_ID");
				
//				System.out.println("User id: " + uid);
				
				theList.add(uid);
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

		return theList;
	}
	
	public List<String>readAllUN(){
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<String> theList = new ArrayList<String>();

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				User u = new User();
				
				String un = rs.getString("User_Name");
				
				System.out.println("User name: " + un);
				
				theList.add(un);
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

		return theList;
	}

	public List<User> readAllLogins() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<User> theList = new ArrayList<User>();

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				User u = new User();

				int uid = rs.getInt("User_ID");
				String un = rs.getString("User_Name");
				String p = rs.getString("Pass_word");
				String f = rs.getString("F_Name");
				String l = rs.getString("L_Name");
				
//				System.out.println("User id: " + uid);
//				System.out.print("\t User Name: " + un);
//				System.out.print("\t Password: " + p);
//				System.out.print("\t First name: " + f);
//				System.out.print("\t Last name: " + l+"\n");
				
				u.setUserID(uid);
				u.setUsername(un);
				u.setPassword(p);
				u.setfName(f);
				u.setlName(l);
				theList.add(u);
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

		return theList;
	}

	// -------------------------------------------------------------
	/*
	 * System login and registration helper methods
	 */
	public boolean uniqueUsername(String s) {
		boolean unique = true;

		List<String> users = readAllUN();

		for (String s1 : users) {
			if (s.equals(s1)) {
				unique = false;
			}
		}

		return unique;
	}

	public boolean validateUserByName(String s1, String s2) {
		List<User> users = readAllLogins();
		boolean valid = true;

		for (User u : users) {
			if (u.getfName().equals(s1)) {
				if (u.getlName().equals(s2)) {
					valid = false;
				}
			}
		}

		return valid;
	}

	public boolean isAUserName(String input) {
		
		List<String> users = readAllUN();
		
		System.out.println("Is a user name..");

		System.out.println("Input "+input);
//		if (users.contains(input)) {
//			return true;
//		}

		for(String s: users) {
			if(s.equals(input)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isAUserID(int input) {
		List<Integer> users = readAllUID();
		
		System.out.println("Is a user id..");
		
		System.out.println("Input "+input);
//		if (users.contains(new Integer(input))) {
//			return true;
//		}

		for(int i: users) {
			if(i == input) {
				return true;
			}
		}		
		
		return false;
	}
	
	public boolean isValidLoginFirst(String input) {
		int login = -1;
		boolean valid = false;
		if (input.matches("^[0-9]+")) {
			System.out.println("numbers");
			login = Integer.parseInt(input);
		} else {
			System.out.println("string");
		}
		
		if(login != -1) {
			valid = isAUserID(login);
		}else if(login == -1) {
			valid = isAUserName(input);
		}
		

		return valid;
	}

	public boolean correctPassword(String un,String p) {
		int login = -1;
		
		if (un.matches("^[0-9]+")) {
			System.out.println("numbers");
			login = Integer.parseInt(un);
			User u = readLogin(login);
			if(u.getPassword().equals(p)) {
				return true;
			}
		} else {
			System.out.println("string");
			List<User> users = readAllLogins();
			for(User u: users) {
				if(u.getUsername().equals(un)) {
					if(u.getPassword().equals(p)) {
						return true;
					}
				}
			}
		}		
		
		return false;

	}
	
	// ----------------------------------------------------------------------

	public void changePassword(User u, String pass) {

		if (u != null) {
			PreparedStatement pStmt1 = null;

			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "Update From BALogin Set Pass_word = ? Where User_Name = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setString(1, pass);
				pStmt1.setString(2, u.getUsername());

				pStmt1.executeUpdate();
				System.out.println("Password changes and User updated.");
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

	}

	public void changePassword(int uid, String pass) {

		PreparedStatement pStmt1 = null;

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Update From BALogin Set Pass_word = ? Where User_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setString(1, pass);
			pStmt1.setInt(2, uid);

			pStmt1.executeUpdate();
			System.out.println("Password changes and User updated.");
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

}
