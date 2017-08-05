package com.revature.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.BankUser;
import com.revature.util.ApplicationSession;
import com.revature.util.ConnectionUtil;

public class Main {
	
	private static ApplicationSession as = null;
	private static Scanner s = null;
	private static BankDAO bankdao = null;

	public static void main(String[] args) {
		
		/*
		try(Connection c = ConnectionUtil.getConnection()){
			System.out.println("success?");
		} catch (SQLException e) {
			System.out.println("failed to connect");
			e.printStackTrace();
		}
		*/
		as = ApplicationSession.startApplicationSession();
		bankdao = new BankDAOImpl();
		s = new Scanner(System.in);
		System.out.println("Welcome to banking app!");
		String input = "";
		while(!input.equalsIgnoreCase("quit")){
			System.out.println("Are you a returning user or a new user?");
			input = s.nextLine();
			if(input.equalsIgnoreCase("new")) {
				createUserFromInput();
			}
			else if(input.equalsIgnoreCase("returning")) {
				loginFromInput();
			}
		}
		s.close();
	}
	
	public static BankUser createUserFromInput(){
		
		System.out.println("Enter username");
		String username = s.nextLine();
		System.out.println("Enter password");
		String pass = s.nextLine();
		System.out.println("Enter first name");
		String firstname = s.nextLine();
		System.out.println("Enter last name");
		String lastname = s.nextLine();
		
		BankUser newUser = new BankUser(username, pass, firstname, lastname);
		bankdao.createBankUser(newUser);
		return newUser;
	}
	
	public static void loginFromInput() {
		System.out.println("Enter your username");
		String username = s.nextLine();
		System.out.println("Enter your password");
		String pass = s.nextLine();
		BankUser oldUser = bankdao.getUserFromInfo(username, pass);
		bankdao.login(oldUser);
	}
	
	public static void invalidCommand() {
		System.out.println("Invalid command. Type \"help\" for a list of commands.");
	}
}
