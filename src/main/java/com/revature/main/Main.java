package com.revature.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import com.revature.customers.Customer;
import com.revature.superuser.SuperUser;
import com.revature.users.User;


@SuppressWarnings("resource")
public class Main {
	public static void main(String[] args) {
		// USER NEEDS TO LOG INTO AN ACCOUNT
		System.out.println("Welcome to Josh's banking app.\nWould you like to Log In (L) "
				+ "or Register an account (R)? ");
		Scanner sc = new Scanner(System.in);
		User user = null;
		String input;
		do {
			input = sc.next();
			if(input.toUpperCase().charAt(0) == 'R')
				user = register();
			if(input.toUpperCase().charAt(0) == 'L')
				user = login();
		}while(user == null);
		System.out.println("Login succeeded. Signed in as " + user.getUsername() + ".");
		// USER IS LOGGED INTO AN ACCOUNT
		do {
			user.menu();
			input = sc.next();
			user.execute(input.toUpperCase().charAt(0));
		}while(input.toUpperCase().charAt(0) != 'L');
		System.out.println("Have a nice day!");
		sc.close();
	}
	
	public static User login() {
		Scanner sc = new Scanner(System.in);
		User user;
		do {
			System.out.print("Username/UserId: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			user = getUser(username,password);
			if(user == null)
				System.out.println("Incorrect login information. Try again.");
		} while(user == null);
		return user;
	}
	public static User register() {
		Scanner sc = new Scanner(System.in);
		Customer user;
		do {
			user = new Customer();
			System.out.print("Username/UserId: ");
			String username = sc.next();
			System.out.print("Password: ");
			String password = sc.next();
			user = (Customer) user.uDao.checkUser(username);
			if(user != null || username.equals("admin")) {
				System.out.println("Username already exists. Please choose another name");
				user = null;
			}
			else {
				user = new Customer();
				user.uDao.addUser(username, password);
				user = (Customer) user.uDao.getUser(username, password);
			}
		} while(user == null);
		return user;
	}
	
	public static User getUser(String username, String password){
		Properties prop = new Properties();
		InputStream input = null;
		User user = null;
		try {
			input = new FileInputStream("src/main/java/config.properties");
			// load a properties file
			prop.load(input);
			// get the properties value
			String name = prop.getProperty("superName");
			String pass = prop.getProperty("superPassword");
			if((username.equals(name)) && (password.equals(pass))) {
				user = new SuperUser(name,0);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(user == null) {
			user = new Customer();
			user = user.uDao.getUser(username, password);
		}
		return user;
	}
}
