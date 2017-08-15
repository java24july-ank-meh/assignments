package com.revature.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	@Override
	public List<Reimbursement> readAllReimb() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();

		String sql1 = "";
		try (Connection conn = ConnectionUtil.getConection()) {
			sql1 = "Select * from Reimbursements";
			pStmt1 = conn.prepareStatement(sql1);
			
			rs = pStmt1.executeQuery();
			
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				int r_id = rs.getInt("R_ID");
				double a = rs.getDouble("R_Amount");
				int aid = rs.getInt("U_ID_Author");
				String de = rs.getString("R_Description");
				Blob rec = rs.getBlob("R_Receipt");
				Timestamp res = rs.getTimestamp("R_Resolved");
				int rid = rs.getInt("U_ID_Resolver");
				int st = rs.getInt("RT_Type");
				Timestamp sub = rs.getTimestamp("R_Submitted");
				int ty = rs.getInt("RT_Status");
				
				reim.setrID(r_id);
				reim.setAmount(a);
				reim.setAuthorID(aid);
				reim.setDescription(de);
				reim.setReceipt(rec);
				reim.setResolved(res);
				reim.setResolverID(rid);
				reim.setStatus(st);
				reim.setSubmitted(sub);
				reim.setType(ty);
				reims.add(reim);
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
		
		return reims;
	}

	@Override
	public List<Reimbursement> readReimbByUser(User u,String s) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();

		String sql1 = "";
		if(s.equals("author")) {
			sql1 = "Select * from Reimbursements where U_ID_Author=?";
		} else if(s.equals("resolver")) {
			sql1 = "Select * from Reimbursements where U_ID_Resolver=?";
		}
		
		try (Connection conn = ConnectionUtil.getConection()) {

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setInt(1, u.getuID());
			
			rs = pStmt1.executeQuery();
			
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				int r_id = rs.getInt("R_ID");
				double a = rs.getDouble("R_Amount");
				int aid = rs.getInt("U_ID_Author");
				String de = rs.getString("R_Description");
				Blob rec = rs.getBlob("R_Receipt");
				Timestamp res = rs.getTimestamp("R_Resolved");
				int rid = rs.getInt("U_ID_Resolver");
				int st = rs.getInt("RT_Type");
				Timestamp sub = rs.getTimestamp("R_Submitted");
				int ty = rs.getInt("RT_Status");
				
				reim.setrID(r_id);
				reim.setAmount(a);
				reim.setAuthorID(aid);
				reim.setDescription(de);
				reim.setReceipt(rec);
				reim.setResolved(res);
				reim.setResolverID(rid);
				reim.setStatus(st);
				reim.setSubmitted(sub);
				reim.setType(ty);
				reims.add(reim);
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
		
		return reims;
	}

	@Override
	public Reimbursement readReimb(int rID) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		Reimbursement reim = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Select * from Reimbursements where R_ID=?";
			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setInt(1, rID);
			
			rs = pStmt1.executeQuery();
			
			while (rs.next()) {
				int r_id = rs.getInt("R_ID");
				double a = rs.getDouble("R_Amount");
				int aid = rs.getInt("U_ID_Author");
				String de = rs.getString("R_Description");
				Blob rec = rs.getBlob("R_Receipt");
				Timestamp res = rs.getTimestamp("R_Resolved");
				int rid = rs.getInt("U_ID_Resolver");
				int st = rs.getInt("RT_Type");
				Timestamp sub = rs.getTimestamp("R_Submitted");
				int ty = rs.getInt("RT_Status");
				
				reim.setrID(r_id);
				reim.setAmount(a);
				reim.setAuthorID(aid);
				reim.setDescription(de);
				reim.setReceipt(rec);
				reim.setResolved(res);
				reim.setResolverID(rid);
				reim.setStatus(st);
				reim.setSubmitted(sub);
				reim.setType(ty);
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
		
		return reim;
	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public void createReimb(Reimbursement r) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status, R_Reciept) Values(?, ?, ?, ?, ?, ?, ?)";

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setDouble(1, r.getAmount());
			pStmt1.setInt(2, r.getAuthorID());
			pStmt1.setString(3, r.getDescription());
			pStmt1.setInt(4, r.getStatus());
			pStmt1.setTimestamp(5, r.getSubmitted());
			pStmt1.setInt(6, r.getType());
			pStmt1.setBlob(7, r.getReceipt());
			
			pStmt1.executeQuery();

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

	@Override
	public void updateReimbReg(Reimbursement r) {//amount, description, submit time, author, type ,and status
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Update Reimbursements Set R_Amount=?, R_Description=?, R_Submitted=?, U_ID_Author=?, RT_Type=?, RT_Status=?, R_Reciept=? Where R_ID=?"; 

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setDouble(1, r.getAmount());
			pStmt1.setInt(2, r.getAuthorID());
			pStmt1.setString(3, r.getDescription());
			pStmt1.setInt(4, r.getStatus());
			pStmt1.setTimestamp(5, r.getSubmitted());
			pStmt1.setInt(6, r.getType());
			pStmt1.setBlob(7, r.getReceipt());
			
			pStmt1.setInt(8, r.getrID());
			
			pStmt1.executeQuery();

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
	
	@Override
	public void updateReimbResolved(Reimbursement r) {//amount, description, submit time, author, type ,and status
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Update Reimbursements Set U_ID_Resolver=?, R_Resolved=?, RT_Type=?, RT_Status=? Where R_ID=?"; 

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setInt(1, r.getResolverID());
			pStmt1.setTimestamp(2, r.getResolved());
		
			pStmt1.setInt(3, r.getType());
			pStmt1.setInt(4, r.getStatus());

			pStmt1.setInt(5, r.getrID());
			
			pStmt1.executeQuery();

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
	
	@Override
	public void updateReimbReciept(Reimbursement r) {//amount, description, submit time, author, type ,and status
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Update Reimbursements Set R_Receipt=? Where R_ID=?"; 

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setBlob(1, r.getReceipt());
			pStmt1.setInt(2, r.getrID());
			
			pStmt1.executeQuery();

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

	@Override
	public void deleteReimb(int rID) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Delete from Reimbursements Where R_ID=?"; 

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(2, rID);
			
			pStmt1.executeQuery();

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
	
	
	public String resolvedReimbursement(int author, int mgId, int type, int status ) {
		String message = null;
		
		PreparedStatement prep = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConection()){
			String sql = "UPDATE REIMBURSEMENTS SET U_ID_RESOLVER = ?, RT_TYPE = ?, RT_STATUS = ? WHERE U_ID_AUTHOR =? ";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, mgId);
			prep.setInt(2,type);
			prep.setInt(3, status);
			prep.setInt(4, author);
			rs = prep.executeQuery();
			if(rs.next()) {
				message = "File updated!";
			} else {
				message = "File not updated!";
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return message;
	}
	
	

}
