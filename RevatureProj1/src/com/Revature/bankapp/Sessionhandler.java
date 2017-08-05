package com.Revature.bankapp;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.Revature.rsa.rsahandler.Driver;

public class Sessionhandler {
	private int sessionID;
	private String userID;
	private String passenc;
	private int currentbalance;
	
	public void login() throws InterruptedException{
		Driver rsadriver = new Driver();
		Scanner in = new Scanner(System.in);
		System.out.println("ENTER USERNAME");
		userID = in.nextLine();
		System.out.println("ENTER PASSWORD");
		rsadriver.encrypt(in.nextLine());
		passenc = rsadriver.getOutput();
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try(Connection conn = ConnectionUtil.getConnection("Superuser", "p4ssw0rd")){
			System.out.println("OBTAINING USER INFO...");
			TimeUnit.MILLISECONDS.sleep(300);
			String sql = "SELECT * FROM bankusers WHERE B_NAME = ? AND B_PASS = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  userID);
            pstmt.setString(2, passenc);
            rs = pstmt.executeQuery();
            rs.next();
            if(rs == null){
            	System.out.println("USER DOES NOT EXIST!");
            	return;
            }
			currentbalance = Integer.parseInt(rs.getString("B_BALANCE"));
		}catch(SQLException e){
			System.out.println("Error: USER DOES NOT EXIST!");
			return;
		}finally{
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
            if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		TimeUnit.MILLISECONDS.sleep(300);
		menu();
	}
	public void welcomeuser(){
		String s = "Welcome " + userID + ", Your balance is: " + currentbalance + "$. \n";
		System.out.println(s);
	}
	private void menu() throws InterruptedException {
		welcomeuser();
		Scanner in = new Scanner(System.in);
		System.out.println("Press 1 to Deposit, 2 to Withdraw, 3 to Delete Account, 4 to Log Out");
		String case1 = in.nextLine();
		while(!case1.equals("4")){
			switch(case1){
			case("1"): deposit(); break;
			case("2"): withdraw(); break;
			case("3"): delete(); return;
			}
			TimeUnit.MILLISECONDS.sleep(300);
			welcomeuser();
			System.out.println("Press 1 to Deposit, 2 to Withdraw, 3 to Delete Account, 4 to Log Out");
			case1 = in.nextLine();
		}
		
	}
	private void delete() {
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String input = "";
        System.out.println("YOU MUST HAVE A BALANCE OF 0 TO DELETE YOUR ACCOUNT!");
        Scanner in = new Scanner(System.in);
        while(!input.equals("N")&&!input.equals("Y")){
        	System.out.println("Continue? Y/N: ");
        	input = in.nextLine();
        }
        if(input.equals("N")){
        	return;
        }
		try(Connection conn = ConnectionUtil.getConnection("Superuser", "p4ssw0rd")){
			System.out.println("Connected to database...");
			String sql = "SELECT * FROM bankusers WHERE B_NAME = ? AND B_PASS = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  userID);
            pstmt.setString(2, passenc);
            rs = pstmt.executeQuery();
            rs.next();
			int balance = Integer.parseInt(rs.getString("B_BALANCE"));
			if(balance == 0){
				sql = "DELETE FROM bankusers WHERE B_NAME = ? AND B_PASS = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userID);
				pstmt.setString(2, passenc);
	            pstmt.executeQuery();
			}
			else{
				System.out.println("Error: Balance not zero! Logging out...");
			}
			
		}catch(SQLException e){
			System.out.println("Error!");
			e.printStackTrace();
		}finally{
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
            if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}
	private void withdraw() {
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner in = new Scanner(System.in);
		try(Connection conn = ConnectionUtil.getConnection("Superuser", "p4ssw0rd")){
			System.out.println("Connected to database...");
			System.out.println("ENTER AMOUNT TO WITHDRAW: ");
			int input = in.nextInt();
			String sql = "SELECT * FROM bankusers WHERE B_NAME = ? AND B_PASS = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  userID);
            pstmt.setString(2, passenc);
            rs = pstmt.executeQuery();
            rs.next();
			int balance = Integer.parseInt(rs.getString("B_BALANCE"));
			if(input <= balance){
				int newbalance = balance - input;
				sql = "BEGIN PR_DEPOSIT(?, ?, ?); END;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userID);
				pstmt.setString(2, passenc);
				pstmt.setInt(3, newbalance);
				pstmt.executeQuery();
				currentbalance = newbalance;
			}
			else{
				System.out.println("Error: Attempted to withdraw more than you own.");
			}
			
		}catch(SQLException e){
			System.out.println("Error!");
			e.printStackTrace();
		}finally{
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
            if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}
	private void deposit() {
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner in = new Scanner(System.in);
		try(Connection conn = ConnectionUtil.getConnection("Superuser", "p4ssw0rd")){
			System.out.println("Connected to database...");
			System.out.println("ENTER AMOUNT TO DEPOSIT: ");
			int input = in.nextInt();
			String sql = "SELECT * FROM bankusers WHERE B_NAME = ? AND B_PASS = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  userID);
            pstmt.setString(2, passenc);
            rs = pstmt.executeQuery();
            rs.next();
			int balance = Integer.parseInt(rs.getString("B_BALANCE"));
			int newbalance = balance + input;
			sql = "BEGIN PR_DEPOSIT(?, ?, ?); END;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, passenc);
			pstmt.setInt(3, newbalance);
			pstmt.executeQuery();
			currentbalance = newbalance;
		}catch(SQLException e){
			System.out.println("Error");
			e.printStackTrace();
		}finally{
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
            if (rs != null) {try {rs.close();} catch(SQLException e) {e.printStackTrace();}}
		}
		
	}
	public void register(){
		Driver rsadriver = new Driver();
		Scanner in = new Scanner(System.in);
		System.out.println("ENTER USERNAME");
		userID = in.nextLine();
		System.out.println("ENTER PASSWORD");
		rsadriver.encrypt(in.nextLine());
		String compare = rsadriver.getOutput();
		System.out.println("CONFIRM PASSWORD");
		rsadriver.encrypt(in.nextLine());
		if(compare.equals(rsadriver.getOutput())){
			passenc = rsadriver.getOutput();
			resgisterrequest();
		}
		else{
			System.out.println("Error: Passwords do not match");
			return;
		}
		
	}
	private void resgisterrequest() {
		PreparedStatement pstmt = null;
		try(Connection conn = ConnectionUtil.getConnection("Superuser", "p4ssw0rd")){
			System.out.println("Connected to database...");
			System.out.println("Registering..");
			String sq1 = "BEGIN PR_CREATE_USER(? , ?); END;";
			pstmt = conn.prepareStatement(sq1);
			pstmt.setString(1, userID);
			pstmt.setString(2, passenc);
			pstmt.execute();
		}catch(SQLException e){
			System.out.println("ERROR");
			e.printStackTrace();
		}finally{
			if (pstmt != null) {try {pstmt.close();} catch(SQLException e) {e.printStackTrace();}}
		}
	}
}
