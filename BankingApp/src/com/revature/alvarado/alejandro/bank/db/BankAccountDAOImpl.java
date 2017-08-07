package com.revature.alvarado.alejandro.bank.db;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.alvarado.alejandro.bank.BankAccount;
import com.revature.alvarado.alejandro.bank.NotEnoughFundsException;
import com.revature.alvarado.alejandro.bank.util.ConnectionUtil;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	@Override
	public List<BankAccount> getAllBankAccountsForUser(int userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BankAccount> accounts = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANKACCOUNT WHERE BANKUSER_ID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int accountId = rs.getInt("BANKACCOUNT_ID");
				double amount = rs.getDouble("BANKACCOUNT_BALANCE");
				
				BankAccount account = new BankAccount(accountId, amount);
				
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
			if (rs != null) {
				try { rs.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return accounts;
	}

	@Override
	public Boolean createBankAccount(Integer userId) {
		PreparedStatement ps = null;
		int rs = 0;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO BANKACCOUNT (BANKUSER_ID) VALUES (?)";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			
			rs = ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return rs > 0;
	}

	@Override
	public Boolean deleteBankAccount(Integer userId, Integer accountId)
			throws AccountNotFoundException {
		PreparedStatement ps = null;
		int rs = 0;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM BANKACCOUNT WHERE BANKUSER_ID = ? AND BANKACCOUNT_ID = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			if (ps != null) {
				try { ps.close();} catch (SQLException e) { e.printStackTrace();}
			}
		}
		
		return rs > 0;
	}

	@Override
	public Integer updateBankAccount(Integer userId, Integer bankId, Integer amount)
			throws AccountNotFoundException, NotEnoughFundsException {
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String success = "";
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String accountsql = "SELECT * FROM BANKACCOUNT WHERE BANKACCOUNT_ID = ? AND BANKUSER_ID = ?";
			ps = conn.prepareStatement(accountsql);
			
			ps.setInt(1, bankId);
			ps.setInt(2, userId);
			
			rs = ps.executeQuery();
			
			
			Integer balance = 0;
			while (rs.next()) {
				balance = rs.getInt("BANKACCOUNT_BALANCE");
			}
			if ((balance + amount) < 0) {
				throw new NotEnoughFundsException("Not enough funds in your account or no account exists");
			}
			
			String sql = "{CALL P_UPDATE_ACCOUNT(?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, bankId);
			cs.setInt(2, userId);
			cs.setInt(3, amount);
			cs.setString(4,  success );
			
			cs.execute();
		
		} catch (SQLException e) {
			throw new AccountNotFoundException("Account was not able to be found");
		} catch (IOException e){
			e.printStackTrace();
		} catch (NotEnoughFundsException e) {
			throw new NotEnoughFundsException("Not enough funds");
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return amount;
	}
}
