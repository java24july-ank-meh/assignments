package com.revature.dao;

import java.sql.*;
import java.lang.reflect.Field;
import java.util.*;

import com.revature.domain.Account;
import com.revature.domain.User;
import com.revature.util.ConnectionUtility;

public class UserDaoImpl implements UserDao {//Works with and for Full User Info
	//but these 'read' methods are full imp, meaning it uses  both tables to pull resources

	@Override
	public void createUser(User u) {//same method as LoginDaoImpl.createLogin()

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
				String sql1 = "Insert Into BALogin(Username, Password, F_Name, L_Name) Values(?,?,?,?)";

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
	// ------------------------------------------------------------------

	@Override
	public User readUser(int id) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		User u = new User();
		u.setUserID(id);

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BAUserInfo Where MyUser_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				String b = rs.getString("Total_Balance");
				String m = rs.getString("M_Name");
				String a = rs.getString("Address");
				String c = rs.getString("City");
				String s = rs.getString("State");
				String pc = rs.getString("PostalCode");
				u.setmName(m);
				u.setAddress(a);
				u.setCity(c);
				u.setState(s);
				u.setPostalCode(pc);
				u.setTotalBalance(Double.parseDouble(b));
				System.out.println("User ID: " + id);
				System.out.print("\t Middle name: " + m );
				System.out.print("\t Total Balance: " + b);
				System.out.print("\t Address: " + a);
				System.out.print("\t City, State  Postal Code: " + c + ", " + s + " " + pc);
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

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select * From BALogin Where User_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			rs = pStmt1.executeQuery();

			while (rs.next()) {

				String un = rs.getString("User_Name");
				String p = rs.getString("Password");
				String f = rs.getString("F_Name");
				String l = rs.getString("L_Name");

//				System.out.println("User Name: " + un);
//				System.out.print("\t Password: " + p);
//				System.out.print("\t First name: " + f);
//				System.out.print("\t Last name: " + l);

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

	@Override
	public List<User> readAllUsers() {

		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Integer> theList = new ArrayList<Integer>();
		List<User> users = new ArrayList<User>();

		LoginDaoImpl lD = new LoginDaoImpl();
		theList = lD.readAllUID();

		User u2; 
		for(Integer u: theList) {
			u2= readUser(u);
			users.add(u2);
		}

		return users;
	}

	// ------------------------------------------------------------------------------
	@Override
	public User updateUserL(User u) {
		if (u != null) {
			PreparedStatement pStmt1 = null;

			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "Update From BALogin Set User_Name =?, Password = ?, F_Name = ?, L_Name = ? Where User_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setString(1, u.getUsername());
				pStmt1.setString(2, u.getPassword());
				pStmt1.setString(3, u.getfName());
				pStmt1.setString(4, u.getlName());
				pStmt1.setInt(5, u.getUserID());

				pStmt1.executeUpdate();
				System.out.println("User updated.");
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
		return u;
	}

	@Override
	public User updateUserI(User u) {
		if (u != null) {
			PreparedStatement pStmt1 = null;

			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "Update From BAUserInfo Set M_Name =?, Address = ?, City = ?, State = ?, PostalCode = ? Where MyUser_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setString(1, u.getmName());
				pStmt1.setString(2, u.getAddress());
				pStmt1.setString(3, u.getCity());
				pStmt1.setString(4, u.getState());
				pStmt1.setString(5, u.getPostalCode());
				pStmt1.setInt(6, u.getUserID());

				pStmt1.executeUpdate();
				System.out.println("User updated.");
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
		return u;
	}

	public User compileUpdatedValues(User u, User u1) {

		String f = u.getfName();
		String m = u.getmName();
		String l = u.getlName();
		String a = u.getAddress();
		String c = u.getCity();
		String s = u.getState();
		String pc = u.getPostalCode();

		if (!f.equals(".")) {
			u1.setfName(f);
		} else if (!m.equals(".")) {
			u1.setmName(m);
		} else if (!l.equals(".")) {
			u1.setlName(l);
		} else if (!a.equals(".")) {
			u1.setAddress(a);
		} else if (!c.equals(".")) {
			u1.setCity(c);
		} else if (!s.equals(".")) {
			u1.setState(s);
		} else if (!pc.equals(".")) {
			u1.setPostalCode(pc);
		}

		return u1;
	}
	// ---------------------------------------------------------------------------------

	@Override
	public void deleteUser(int id) {

		PreparedStatement pStmt1 = null;

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Delete From BAUserInfo Where MyUser_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			pStmt1.executeUpdate();
			System.out.println("Account deleted completely.");
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
	public boolean deleteUser(User u) {
		boolean done = false;

		if (u.getTotalBalance() == 0 && u != null) {
			PreparedStatement pStmt1 = null;

			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "Delete From BAUserInfo Where MyUser_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setInt(1, u.getUserID());

				pStmt1.executeUpdate();
				System.out.println("Account deleted completely.");
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
			done = true;
		}

		return done;
	}

	// ---------------------------------------------------
	@Override
	public double userTotalBalance(int id) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		double b = 0.0;

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "Select Total_Balance From BALogin Where User_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, id);

			rs = pStmt1.executeQuery();

			while (rs.next()) {
				b = rs.getDouble("Total_Balance");
				System.out.println("Total Balance: " + b);
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

		return b;
	}

	@Override
	public double userTotalBalance(User u) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		double b = 0.0;

		if (u != null) {
			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "Select Total_Balance From BALogin Where MyUser_ID = ?";

				pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setInt(1, u.getUserID());

				rs = pStmt1.executeQuery();

				while (rs.next()) {
					b = rs.getDouble("Total_Balance");
					System.out.println("Total Balance: " + b);
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
		}

		return b;
	}

	public double userTotalBalanceSP(User u) {
		CallableStatement	cStmt1 = null;
		ResultSet rs = null;
		double b = 0.0;

		if (u != null) {
			try (Connection conn = ConnectionUtility.getConectionProperties()) {
				String sql1 = "? = Call calc_TotalBalance(?)";

				cStmt1 = conn.prepareCall(sql1);
				cStmt1.setInt(2, u.getUserID());

				cStmt1.registerOutParameter(1, Types.DOUBLE);

				cStmt1.execute();

				while (rs.next()) {
					b = rs.getDouble(1);
					System.out.println("Total Balance: " + b);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (cStmt1 != null) {
					try {
						cStmt1.close();
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
		}

		return b;
	}
	public double userTotalBalanceSP(int n) {
		CallableStatement	cStmt1 = null;
		ResultSet rs = null;
		double b = 0.0;

		try (Connection conn = ConnectionUtility.getConectionProperties()) {
			String sql1 = "? = Call calc_TotalBalance(?)";

			cStmt1 = conn.prepareCall(sql1);
			cStmt1.setInt(2, n);

			cStmt1.registerOutParameter(1, Types.DOUBLE);

			cStmt1.execute();

			while (rs.next()) {
				b = rs.getDouble(1);
				System.out.println("Total Balance: " + b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cStmt1 != null) {
				try {
					cStmt1.close();
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

		return b;
	}
}
