package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Transaction;
import com.revature.domain.User;
import com.revature.util.ConnectionUtility;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public void createTransaction(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransaction(Transaction t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Transaction> readAllTransaction() {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Transaction> tL = new ArrayList<Transaction>();

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Select * From BATransaction";

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while(rs.next()) {
				Transaction t = new Transaction();
				int aid = rs.getInt("MyAccount_ID");
				int uid = rs.getInt("MyUser_ID");
				String t1 = rs.getString("Transaction_Type");
				double amt = rs.getDouble("Transaction_Amount");
				String t2 = rs.getString("Transaction_Time");
				t.setIdOfAccount(aid);
				t.setUserID(uid);
				t.setType(t1);
				t.setAmountOfTransaction(amt);
				t.setTime(t2);
				System.out.println("User ID: "+uid+" and Account ID: "+aid);
				System.out.print("\t type: "+t1);
				System.out.print("\t amount: "+amt);
				System.out.print("\t time: "+t2);
				tL.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return tL;
	}

	@SuppressWarnings({ "null", "resource" })
	@Override
	public List<Transaction> readAllTransaction(String s, int id) {
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;
		List<Transaction> tL = new ArrayList<Transaction>();

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "";

			if(s.equals("user")) {
				sql1 = "Select * From BATransaction Where MyUser_ID = ?";	
			}else if(s.equals("account")) {
				sql1 = "Select * From BATransaction Where MyAccount_ID = ?";
			}

			pStmt1.setInt(1, id);

			pStmt1 = conn.prepareStatement(sql1);

			rs = pStmt1.executeQuery();

			while(rs.next()) {
				Transaction t = new Transaction();
				int aid = rs.getInt("MyAccount_ID");
				int uid = rs.getInt("MyUser_ID");
				String t1 = rs.getString("Transaction_Type");
				double amt = rs.getDouble("Transaction_Amount");
				String t2 = rs.getString("Transaction_Time");
				t.setIdOfAccount(aid);
				t.setUserID(uid);
				t.setType(t1);
				t.setAmountOfTransaction(amt);
				t.setTime(t2);
				System.out.println("User ID: "+uid+" and Account ID: "+aid);
				System.out.print("\t type: "+t1);
				System.out.print("\t amount: "+amt);
				System.out.print("\t time: "+t2);
				tL.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return tL;
	}

	@Override
	public void deleteTransaction(int tid) {

		PreparedStatement pStmt1 = null;

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Delete From BATransaction Where Transaction_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, tid);

			pStmt1.executeUpdate();
			System.out.println("Transaction deleted completely.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void deleteAllAccountTransaction(int aid) {

		PreparedStatement pStmt1 = null;

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Delete From BATransaction Where MyAccount_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, aid);

			pStmt1.executeUpdate();
			System.out.println("Account transactions deleted completely.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void deleteAllUserTransaction(int uid) {
		PreparedStatement pStmt1 = null;

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Delete From BATransaction Where MyUser_ID = ?";

			pStmt1 = conn.prepareStatement(sql1);
			pStmt1.setInt(1, uid);

			pStmt1.executeUpdate();
			System.out.println("Account transactions deleted completely.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteAllTransaction() {
		PreparedStatement pStmt1 = null;

		try(Connection conn = ConnectionUtility.getConectionProperties()){
			String sql1 = "Delete From BATransaction";

			pStmt1 = conn.prepareStatement(sql1);

			pStmt1.executeUpdate();
			System.out.println("Account transactions deleted completely.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pStmt1 != null) {
				try {
					pStmt1.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
