package com.revature.main;

import java.util.*;
import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.util.ConnectionUtil;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static DataService service = new DataService();
	public static User current_user = null;
	
	public static String superuser = null;
	public static String superpassword = null;
	
	public static void main(String[] argv) {
		Properties prop = ConnectionUtil.getProperties();
		superuser = prop.getProperty("superuser");
		superpassword = prop.getProperty("superpassword");
		
		start(); 
		sc.close();
	}
	
	public static void start() {
		while(true) {
			if(current_user == null)
				System.out.print("bank> ");
			else
				System.out.print(current_user.getUsername()+"@bank> ");
			String line = sc.nextLine();
			String[] args = line.split(" ");
			if(args.length == 0) continue;
			args[0] = args[0].replaceAll("\\s+","");
			 
			if(args[0].equals("")) continue;
			else if(args[0].equals("exit")) break;
			else if(current_user == null)
				loginState(args[0]); //login state
			else if(current_user.getUsername().equals(superuser))
				superuserState(args);
			else
				userState(args); //user state
		 }
	}
	
	public static void loginState(String command) {
		if(command.equalsIgnoreCase("register")) {
			System.out.print("set username: ");
			String username = sc.nextLine();
			System.out.print("set password: ");
			String password = sc.nextLine();
			if(username.equals(superuser)) {
				System.out.println("User already exists");
				return;
			}
			User user = new User(username,password);
			try {
				if(service.createUser(user))
					System.out.println("User " + username + " created");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} else if(command.equalsIgnoreCase("login")) {
			System.out.print("username: ");
			String username = sc.nextLine();
			System.out.print("password: ");
			String password = sc.nextLine();
			User user = new User(username,password);
			if(username.equals(superuser)) {
				if(password.equals(superpassword)) {
					System.out.println("Logged in as superuser");
					current_user = user;
				} else System.out.println("Password authentication failed");
			}
			else try {
				service.verifyUser(user);
				System.out.println("Logged in as " + username);
				current_user = user;
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} else System.out.println("Unrecognized command");
	}
	
	public static void userState(String[] args) {
		if(args[0].equals("logout")) {
			current_user = null;
			return;
		} else if(args[0].equalsIgnoreCase("show")){ //show user accounts
			if(args.length < 2) {
				System.out.println("usage: show accounts");
				System.out.println("       show account [acc_name]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			if(args[1].equalsIgnoreCase("accounts")) {
				List<Account> accounts = service.readAllAccounts(current_user);
				if(accounts.isEmpty()) System.out.println("No accounts");
				else for(Account account: accounts) {
					System.out.println(account.getName() + " - balance: " + account.getBalance());
				}
			} else if(args[1].equalsIgnoreCase("account")) {
				if(args.length != 3) {
					System.out.println("usage: show account [acc_name]");
					return;
				}
				args[2] = args[2].replaceAll("\\s+","");
				Account account = new Account(args[2], current_user.getId());
				service.readAccount(account);
				System.out.println("balance: " + account.getBalance());
			}
		} else if(args[0].equalsIgnoreCase("create")){ //create account
			if(args.length != 3) {
				System.out.println("usage: create account [acc_name]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			args[2] = args[2].replaceAll("\\s+","");
			if(args[1].equals("account")) {
				Account account = new Account(args[2],current_user.getId());
				service.createAccount(account);
			} else System.out.println("usage: create account [acc_name]");
		} else if(args[0].equalsIgnoreCase("delete")){ //delete account
			if(args.length != 3) {
				System.out.println("usage: delete account [acc_name]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			args[2] = args[2].replaceAll("\\s+","");
			if(args[1].equals("account")) {
				Account account = new Account(args[2],current_user.getId());
				try {
					service.deleteAccount(account);
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			} else System.out.println("usage: delete account [acc_name]");
		} else if(args[0].equalsIgnoreCase("withdraw")) { //withdraw
			if(args.length != 4) {
				System.out.println("usage: withdraw from [acc_name] [amount]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			args[2] = args[2].replaceAll("\\s+","");
			args[3] = args[3].replaceAll("\\s+","");
			if(args[1].equals("from")) {
				try {
					double amount = Double.parseDouble(args[3]);
					Account account = new Account(args[2],current_user.getId());
					try {
						service.withdraw(account, amount);
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} catch (NumberFormatException e) {
					System.out.println("usage: withdraw from [acc_name] [amount]");
					return;
				}
			} else System.out.println("usage: withdraw from [acc_name] [amount]");
		} else if(args[0].equalsIgnoreCase("deposit")) { //deposit
			if(args.length != 4) {
				System.out.println("usage: deposit to [acc_name] [amount]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			args[2] = args[2].replaceAll("\\s+","");
			args[3] = args[3].replaceAll("\\s+","");
			if(args[1].equals("to")) {
				try {
					double amount = Double.parseDouble(args[3]);
					Account account = new Account(args[2],current_user.getId());
					service.deposit(account, amount);
				} catch (NumberFormatException e) {
					System.out.println("usage: deposit to [acc_name] [amount]");
					return;
				}
			} else System.out.println("usage: deposit to [acc_name] [amount]");
		} else System.out.println("Unrecognized command");
	}
	
	public static void superuserState(String[] args) {
		if(args[0].equals("logout")) {
			current_user = null;
			return;
		} else if(args[0].equalsIgnoreCase("show")){ //show user accounts
			if(args.length < 2) {
				System.out.println("usage: show users");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			if(args[1].equalsIgnoreCase("users")) {
				List<User> users = service.readAllUsers();
				if(users.isEmpty()) System.out.println("No users");
				else for(User user: users) {
					System.out.println(user.getUsername());
				}
			} else System.out.println("usage: show users");
		} else if(args[0].equalsIgnoreCase("delete")) {
			if(args.length < 2) {
				System.out.println("usage: delete user [username]");
				return;
			}
			args[1] = args[1].replaceAll("\\s+","");
			args[2] = args[2].replaceAll("\\s+","");
			if(args[1].equalsIgnoreCase("user")) {
				User user = new User(args[2],null);
				try {
					if(service.deleteUser(user))
						System.out.println("user deleted");;
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			} else System.out.println("usage: delete user [username]");
		}
	}
}
