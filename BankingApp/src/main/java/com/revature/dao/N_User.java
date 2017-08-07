package com.revature.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

import com.revature.domain.Accounts;

import com.revature.util.Connect;

public class N_User implements NormalUserFace {

	//Create a Scanner
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void createAccount() {
		PreparedStatement pstmt = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			
			//Get the infomation need to build a new entry
			System.out.println("Please enter your initial balance: ");
			float val = scan.nextFloat();
			System.out.println("This is val: " + val);
			System.out.println("Please enter your firstname: ");
			String f = scan.next();
			System.out.println("This is f: " + f);
			System.out.println("Please enter your Client ID: ");
			int i = scan.nextInt();

			//Create a prepared statement
			String add = "INSERT INTO Client_Accounts(Acc_Value, C_FirstName, C_ID) "
					+ "VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(add);
			pstmt.setFloat(1, val);
			pstmt.setString(2, f);
			pstmt.setInt(3, i);
			
			
			pstmt.executeQuery();
			System.out.println("Client " + f + " has created an account with " + val + " dollars in it.");
			
		} catch (SQLException | IOException e) {
			System.out.println("Failed to create an account");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	@Override
	public void viewAccount(int a_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Client_Accounts WHERE Acc_ID= ?";
			pstmt = conn.prepareStatement(read);
			pstmt.setInt(1, a_id);
			
			rs = pstmt.executeQuery();
			System.out.println("Accessing Account Database");
			while(rs.next()) {
				String accountId = rs.getString("Acc_ID");
				String accountBalance = rs.getString("Acc_Value");
				String clientFirstname = rs.getString("C_FirstName");
				String clientID = rs.getString("C_ID");
				
				System.out.println("Account ID: " + accountId);
				System.out.println("Account Balance: " + accountBalance);
				System.out.println("FirstName: " + clientFirstname);
				System.out.println("ClientID: " + clientID);
			}
		} catch (SQLException | IOException e) {
			System.out.println("Failed to view account in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}

	@Override
	public List<Accounts> viewAllAccounts(int u_ID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Accounts> accountList = new ArrayList<>();
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Client_Accounts WHERE C_ID=?";
			pstmt = conn.prepareStatement(read);
			pstmt.setInt(1, u_ID);
			rs = pstmt.executeQuery();
			System.out.println("Accessing Account Database");
			
			while(rs.next()) {
				int accountId = rs.getInt("Acc_ID");
				float accountBalance = rs.getFloat("Acc_Value");
				String clientFirstname = rs.getString("C_FirstName");
				int clientID = rs.getInt("C_ID");
				
				Accounts acc = new Accounts(accountId, accountBalance,clientFirstname, clientID);
				
				accountList.add(acc);
			}
		} catch (SQLException | IOException e) {
			System.out.println("Failed to view clients in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		return accountList;
	}
	

	@Override
	public void deleteAccount(int a_id) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Client_Accounts WHERE Acc_ID= ?";
			pstmt = conn.prepareStatement(read);
			pstmt.setInt(1, a_id);
			
			rs = pstmt.executeQuery();
			System.out.println("Checking the account's balance");
			
			if(rs.getFloat("Acc_Value") == 0) {
				System.out.println("The account is empty. Now deleting account.");
				String remove = "DELETE FROM Client_Accounts WHERE Acc_ID=?";
				pstmt2 = conn.prepareStatement(remove);
				pstmt2.setInt(1, a_id);
				pstmt2.executeUpdate();
				System.out.println("Account deleted successfully.");
			} else {
				System.out.println("Need to empty account of money first.");
			}
		} catch (SQLException | IOException e) {
			System.out.println("Failed to delete account in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (pstmt2 != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}
	
	public void deleteAllAcounts(int u_ID) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			String read = "SELECT * FROM Client_Accounts WHERE C_ID= ?";
			pstmt = conn.prepareStatement(read);
			pstmt.setInt(1, u_ID);
			
			rs = pstmt.executeQuery();
			System.out.println("Checking the account's balance");
			while(rs.next()) {
				if(rs.getFloat("Acc_Value") == 0) {
					System.out.println("The account is empty. Now deleting account.");
					String remove = "DELETE FROM Client_Accounts WHERE Acc_ID=?";
					pstmt2 = conn.prepareStatement(remove);
					pstmt2.setInt(1, rs.getInt(1));
					pstmt2.executeUpdate();
					System.out.println("Account deleted successfully.");
				} else {
					System.out.println("Account " + rs.getInt(1)+ " still has money. Need to empty account of money first.");
				}
			}
		} catch (SQLException | IOException e) {
			System.out.println("Failed to delete account in Database");
			e.printStackTrace();
		}finally {
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (pstmt2 != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
			if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}

	@Override
	public void depositMoney(int aId, String f_Name, float amt) {
		// Using Callable Statements here to access stored procedures
		CallableStatement cs = null;
		//Open up a connection
			try(Connection conn = Connect.connectWithProps()){
				//System.out.println("Please enter the amount you are depositing: ");
				//Float amount = scan.nextFloat();
				String deposit = "{CALL Deposit_Money(?,?,?,?) }";
				cs = conn.prepareCall(deposit);
				
				cs.setInt(1, aId);
				cs.setString(2, f_Name);
				cs.setFloat(3, amt);
				cs.registerOutParameter(4, java.sql.Types.FLOAT);
				
				boolean result = cs.execute();
				Float amount = cs.getFloat(4);
				if(result == true) {
					System.out.println("Your current balance is: " + amount);
				} else {
					System.out.println("Failure to add additional funds to account.");
				}
			}catch(SQLException | IOException e) {
				System.out.println("Failure to add additional funds to account.");
				e.printStackTrace();
			}finally {
				if (cs != null) {try {cs.close();} catch(SQLException e) {e.printStackTrace();}}
				
			}
	}

	@Override
	public void withdrawtMoney(int aId, String f_Name, float amt) {
		// Using Callable Statements here to access stored procedures
		CallableStatement cs = null;
		//Open up a connection
		try(Connection conn = Connect.connectWithProps()){
			//System.out.println("Please enter the amount you are depositing: ");
			//Float amount = scan.nextFloat();
			String deposit = "{CALL Withdraw_Money(?,?,?,?) }";
			cs = conn.prepareCall(deposit);
			
			cs.setInt(1, aId);
			cs.setString(2, f_Name);
			cs.setFloat(3, amt);
			cs.registerOutParameter(4, java.sql.Types.FLOAT);
			
			boolean result =cs.execute();
			Float amount = cs.getFloat(4);
			if(result == true) {
				System.out.println("Your current balance is: " + amount);
			} else {
				System.out.println("Overdraft: Deficient funds within this account.");
			}
		}catch(SQLException | IOException e) {
			System.out.println("Failure to withdraw funds from account.");
			e.printStackTrace();
		}finally {
			if (cs != null) {try {cs.close();} catch(SQLException e) {e.printStackTrace();}}
			
		}
	}



}
