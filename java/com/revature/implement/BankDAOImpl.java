package com.revature.implement;

import java.sql.*;
import com.revature.bankTables.Account;
import com.revature.bankTables.Transaction;
import com.revature.bankTables.User;
import com.revature.conutil.ConnectionUtil;
import com.revature.dao.BankDao;
import com.revature.exceptions.OverdrawnException;

public class BankDAOImpl implements BankDao{


	public void createUser(User a) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String uFirst = a.getFirstName();
			String uLast = a.getLastName();
			String uUser = a.getUserName();
			String uPass = a.getPassWord(); 
			int uPriv = a.getPriv();
			
			//creating a user here 
			String userCreate = "INSERT INTO BANKUSER (FIRSTNAME, LASTNAME, USERNAME, PASSIN, ADMIN_PRIV) VALUES ('"+uFirst+"', '"+uLast+"', '"+uUser+"', '"+uPass+"', '"+uPriv+")";
			Statement stmtUCreate = conn.createStatement();
			//int nRowsAffected = stmtUCreate.executeUpdate(userCreate);
			stmtUCreate.executeQuery(userCreate);
			
			System.out.println("Username "+uUser+" created");
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
	}

	public String readUser(String userName, String passin) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String results ="";
		try(Connection conn = ConnectionUtil.getConnection()){
			String getInfo = "SELECT FIRSTNAME, LASTNAME FROM BANKUSER WHERE USERNAME = "+userName+ " AND PASSIN = "+passin;
			//Statement stmtURead = conn.createStatement();
			stmt = conn.prepareStatement(getInfo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				 results = (rs.getString("FIRSTNAME")+" "+rs.getString("LASTNAME"));
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return results;
	}

	public void updateUser(User a, String firstName, String lastName, String passWord, String userName) {
		PreparedStatement updateStmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String updateInfo = "UPDATE BANKUSER SET FIRSTNAME = '"+firstName+"', LASTNAME = '"+lastName+"', PASSIN = '"+passWord+"' "
					+ "WHERE USERNAME = "+userName;
			updateStmt = conn.prepareStatement(updateInfo);
			updateStmt.executeQuery();
			a.setFirstName(firstName);
			a.setLastName(lastName);
			a.setPassWord(passWord);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteUser(User a) {
		PreparedStatement deleteStmt = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String deleteUser = "DELETE FROM BANKUSER WHERE USERNAME = "+a.getUserName();
			deleteStmt = conn.prepareStatement(deleteUser);
			deleteStmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void createAccount(Account a) {
		PreparedStatement accountCreate = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			//a.getAccountType();
			String createAccount = "INSERT INTO ACCOUNTS (MONEYAMOUNT, ACCOUNT_TYPE) VALUES (0, " + a.getAccountType()+")";
			accountCreate = conn.prepareStatement(createAccount);
			accountCreate.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readAccount(Account a ) {
		
		PreparedStatement moneyGet = null;
		ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
				String readAccount = "SELECT MONEY_AMOUNT FROM ACCOUNTS WHERE USERID ="+a.getUserId()+")";
				moneyGet = conn.prepareStatement(readAccount);
				moneyGet.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//before calling the update statement to take from an account, must call the procedure that validates the amount
	//and throws an exception if there are insufficient funds
	
	@Override 
	public void validateWithdraw(Account a, double take) throws OverdrawnException {
		PreparedStatement checkStmt = null;
		ResultSet validCheck = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			
		}catch(SQLException e) {
			
		}
	}
	@Override
	public void updateWithdrawAccount(Account a, double take) {
		ResultSet rs = null;
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String withdrawSQL = "{CALL WITHDRAW_BAL(?, ?, ?)";
			cs = conn.prepareCall(withdrawSQL);
			
			cs.setInt(1, a.getUserId());
			cs.setDouble(2,  take);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDepositAccount(Account a, double give) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAccount(Account a) {
		// TODO Auto-generated method stub
		
	}

	
	//Get to this part when I can
	
	
//	public void createTransaction(Transaction t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void readTransaction(int id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void updateTransaction(Transaction t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void deleteTransaction(Transaction t) {
//		// TODO Auto-generated method stub
//		
//	}



}
