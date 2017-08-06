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
import com.revature.util.UserSession;

public class Main {
	
	private static ApplicationSession as = null;
	private static UserSession us = null;
	private static Scanner s = null;
	private static BankDAO bankdao = null;

	public static void main(String[] args) {
		
		as = ApplicationSession.startApplicationSession();
		bankdao = new BankDAOImpl();
		
		s = new Scanner(System.in);
		String input = "";
		
		applicationLoop:while(true){
			System.out.println("Welcome to banking app!");
			System.out.println("Are you a returning user or a new user?");
			input = s.nextLine();
			if(input.equalsIgnoreCase("new")) {
				createUserFromInput();
			}
			else if(input.equalsIgnoreCase("returning")) {
				loginFromInput();
			}
			else if(input.equalsIgnoreCase("quit")) {
				as = null;
				System.out.println("Application Session closed");
				break applicationLoop;
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
		us = UserSession.startUserSession(oldUser);
		us.sessionLoop();
		
	}
	
	public static void invalidCommand() {
		System.out.println("Invalid command. Type \"help\" for a list of commands.");
	}
}
