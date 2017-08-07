package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.domain.Transaction;
import com.revature.domain.User;
import com.revature.util.ConnectionUtil;

public class RegularUserDAOImpl extends LoginDAOImpl implements RegularUserDAO {

	@Override
	public boolean deposit(int id, int acct, int amount) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnectionProp()) {
			conn.setAutoCommit(false);
			String sql = "EXECUTE SP_DEPOSIT(?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setInt(2, acct);
			cs.setInt(3, amount);
			/*if (cs.execute()) { // this line is where everything is going wrong
				System.out.println("Deposited $" + amount + " into Account ID's " + acct + " account");
				cs.close();
				conn.setAutoCommit(true);
				return true;
			} else
				System.out.println("Failed, something went wrong, try again");*/
			System.out.println("Unfortunately this method is not working, but you can manuall insert data through the store procedure in Oracle SQL with this command 'EXECUTE SP_DEPOSIT(?,?,?)'");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean withdraw(int id, int amount) {
		System.out.println("Unofortunately this stored procedure and method is not working");
		return false;
	}

	public boolean getTransactionHistory(int account_number) {
		PreparedStatement pstmt = null;
		ArrayList<Transaction> arr = new ArrayList<Transaction>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TRANSACTION WHERE ACCOUNT_NUMBER=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("T_ID");
				int acc_num = rs.getInt("ACCOUNT_NUMBER");
				String last = rs.getString("T_TYPE");
				int first = rs.getInt("T_AMOUNT");

				Transaction t = new Transaction(id, acc_num, last, first);
				arr.add(t);
			}
			System.out.println(arr);
			rs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
