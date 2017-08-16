package com.doubletrouble.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.doubletrouble.domain.Reimbursements;
import com.doubletrouble.domain.User;
import com.doubletrouble.util.ConnectionUtil;

public class CHERSDaoImpl implements CHERSDao {

	public List<Reimbursements> viewPending() {
		PreparedStatement cs = null;
		int r_id;
		double r_amount;
		String r_description;
		String r_submitted;
		int r_type;
		int u_id_author;
		List<Reimbursements> pending = new ArrayList<Reimbursements>();
		ResultSet reimb = null;
		
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM GET_PENDING_REIMBS";
			cs = conn.prepareStatement(sql);
			reimb = cs.executeQuery();

			while (reimb.next()) {
				r_id = reimb.getInt("R_ID");
				r_amount = reimb.getDouble("R_AMOUNT");
				r_description = reimb.getString("R_DESCRIPTION");
				r_submitted = reimb.getString("R_SUBMITTED");
				r_type = reimb.getInt("RT_TYPE");
				u_id_author = reimb.getInt("U_ID_AUTHOR");
				
				Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, u_id_author);
				c.setType(r_type);
				pending.add(c);

			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return pending;
		}
//		CallableStatement cs = null;
//		int r_id;
//		double r_amount;
//		String r_description;
//		String r_submitted;
//		int r_type;
//		int u_id_author;
//		List<Reimbursements> pending = new ArrayList<Reimbursements>();
//		ResultSet reimb = null;
//		try {
//			Connection conn = ConnectionUtil.getConnectionProp();
//			String sql = "{CALL VIEW_PENDING_REIMBS(?,?,?,?,?,?)}";
//			cs = conn.prepareCall(sql);
//
//			// Set the arguments
//			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(5, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(6, java.sql.Types.VARCHAR);
//			
//			boolean hadResults = cs.execute();
//			System.out.println(hadResults);
//			
//			
//		    while (hadResults) {
//		    	reimb=cs.executeQuery();
//				while (reimb.next()) {
//					r_id = reimb.getInt("RID");
//					r_amount = reimb.getDouble("RAMOUNT");
//					r_description = reimb.getString("RDESCRIPTION");
//					r_submitted = reimb.getString("RSUBMITTED");
//					r_type = reimb.getInt("RTTYPE");
//					u_id_author = reimb.getInt("AUTHOR");
//	
//					Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, u_id_author);
//					System.out.println(c);
//					pending.add(c);
//				}
//		        hadResults = cs.getMoreResults();
//		    }
////			
//			cs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			return pending;
//		}
	}

	public List<Reimbursements> viewResolved() {
		PreparedStatement cs = null;
		int r_id;
		double r_amount;
		String r_description;
		String r_submitted;
		int r_type;
		int u_id_author;
		String r_resolved;
		int ru_resolver;
		int status;
		List<Reimbursements> pending = new ArrayList<Reimbursements>();
		ResultSet reimb = null;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE RT_STATUS != 1";
			cs = conn.prepareStatement(sql);
			reimb = cs.executeQuery();

			while (reimb.next()) {
				r_id = reimb.getInt("R_ID");
				r_amount = reimb.getDouble("R_AMOUNT");
				r_description = reimb.getString("R_DESCRIPTION");
				r_submitted = reimb.getString("R_SUBMITTED");
				r_type = reimb.getInt("RT_TYPE");
				u_id_author = reimb.getInt("U_ID_AUTHOR");
				r_resolved = reimb.getString("R_RESOLVED");
				ru_resolver = reimb.getInt("U_ID_RESOLVER");
				status = reimb.getInt("RT_STATUS");
				
				Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, u_id_author);
				c.setResolved(r_resolved);
				c.setResolver(ru_resolver);
				c.setType(r_type);
				c.setStatus(status);
				pending.add(c);

			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return pending;
		}
	}

	public String getReciept(int id) {
		String byteStream = null;
		CallableStatement cs = null;

		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL GET_RECIEPT(?,?)}";
			cs = conn.prepareCall(sql);

			// Set the arguments
			cs.setInt(1, id);
			cs.registerOutParameter(2, java.sql.Types.BLOB);

			cs.execute();

			byteStream = cs.getString(2);

			cs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return byteStream;
		}
	}

	public void resolveRequest(int r_id, int setState, int resolverID) {
		String byteStream = null;
		CallableStatement cs = null;

		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			// CallableStatement: {CALL PROCEDURE_NAME(?)}
			String sql = "{CALL RESOLVE_REQUEST(?,?,?)}";
			cs = conn.prepareCall(sql);

			// Set the arguments
			cs.setInt(1, r_id);
			cs.setInt(2, setState);
			cs.setInt(3, resolverID);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Reimbursements> viewAllReimbs() {
		List<Reimbursements> all = new ArrayList<Reimbursements>();

		all.addAll(viewPending());
		all.addAll(viewResolved());

		return all;
	}

	@Override
	public List<User> viewAllEmps() {
		
		int u_id;
		String username;
		String password;
		String fName;
		String lName;
		String email;
		
		PreparedStatement cs = null;
		
		List<User> users = new ArrayList<User>();
		ResultSet reimb = null;
		try {
			Connection conn = ConnectionUtil.getConnectionProp();
			String sql = "SELECT * FROM ERS_USERS WHERE UR_ID = 2";
			cs = conn.prepareStatement(sql);
			reimb = cs.executeQuery();

			while (reimb.next()) {
				u_id = reimb.getInt("U_ID");
				username=reimb.getString("U_USERNAME");
				password = reimb.getString("U_PASSWORD");
				fName = reimb.getString("U_FIRSTNAME");
				lName = reimb.getString("U_LASTNAME");
				email = reimb.getString("U_EMAIL");
				
				User c = new User(u_id, username, password, fName,lName,email);
				users.add(c);

			}
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			return users;
		}
	}

	public List<Reimbursements> viewEmpReimbRequests(int id) {
		PreparedStatement cs = null;
//		CallableStatement cs = null;
		int r_id;
		double r_amount;
		String r_description;
		String r_submitted;
		int r_type;
		int u_id_author;
		String r_resolved;
		int ru_resolver;
		
		ResultSet reimb;
		List<Reimbursements> empReimbs = new ArrayList<Reimbursements>();

		try {
			Connection conn = ConnectionUtil.getConnectionProp();
//			String sql = "{CALL GET_EMPLOYEE_REIMBS(?,?,?,?,?,?,?,?,?)}";
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = ?";
//			cs = conn.prepareCall(sql);
			cs = conn.prepareStatement(sql);
			cs.setInt(1,id);
			reimb = cs.executeQuery();
			
			while(reimb.next()) {
			r_id = reimb.getInt("R_ID");
			r_amount = reimb.getDouble("R_AMOUNT");
			r_description = reimb.getString("R_DESCRIPTION");
			r_submitted = reimb.getString("R_SUBMITTED");
			r_type = reimb.getInt("RT_TYPE");
			u_id_author = reimb.getInt("U_ID_AUTHOR");
			r_resolved = reimb.getString("R_RESOLVED");
			ru_resolver = reimb.getInt("U_ID_RESOLVER");
			
			Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, u_id_author);
			if(r_resolved != null) {
				c.setResolved(r_resolved);
				c.setResolver(ru_resolver);
			}
			empReimbs.add(c);
			}
			cs.close();
			} catch (SQLException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			} finally {
			return empReimbs;
			}
	}
			
//			if using a callable statement instead of stored function 
			
//			cs.setInt(1, id);		
//			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(3, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(6, java.sql.Types.NUMERIC);
//			cs.registerOutParameter(7, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(8, java.sql.Types.VARCHAR);
//			cs.registerOutParameter(9, java.sql.Types.NUMERIC);
//		
//			int count=0;
//			boolean results=cs.execute();
//			while(results) {
//				
//				reimb=cs.getResultSet();
//				r_id = reimb.getInt(1);
//				r_amount= reimb.getInt(2);
//				r_description= reimb.getString(3);
//				r_submitted= reimb.getString(4);
//				r_type = reimb.getInt(5);
//				u_id_author=reimb.getInt(6);
//				r_resolved= reimb.getString(7);
//				ru_resolver= reimb.getInt(8);
//				
//				Reimbursements c = new Reimbursements(r_id, r_amount, r_description, r_submitted, u_id_author);
//				if(r_resolved != null) {
//					c.setResolved(r_resolved);
//					c.setResolver(ru_resolver);
//				}
//				results = cs.getMoreResults();
//				empReimbs.add(c);
//			}			
//			
//			cs.close();
//		} catch (SQLException e) {
//		e.printStackTrace();
//		} catch (IOException e) {
//		e.printStackTrace();
//		} finally {
//		return empReimbs;
//		}
//	}
}

