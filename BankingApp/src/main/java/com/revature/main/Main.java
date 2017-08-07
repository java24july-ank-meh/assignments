package com.revature.main;

import java.util.Scanner;

import com.revature.dao.*;
import com.revature.domain.User;
import com.revature.exception.OverdraftException;
import com.revature.exception.SQLStatementFailedException;
import com.revature.exception.UsernameTakenException;

public class Main {
	public static void main(String[] args) {
		BankingDAO dao = new BankingDAOImpl();
		Scanner input = new Scanner(System.in);

		String in;
		System.out.println("Welcome to the Banking App!");
		do {
			System.out.println("\nLOG IN to your accounts.");
			System.out.println("REGISTER a new account.");
			System.out.println("EXIT");
			System.out.println("\n(Acceptible commands are displayed in all capital letters above)");
			in = input.nextLine().toLowerCase();
			switch (in) {
			case "log in":
				System.out.print("username: ");

				String username = input.nextLine();
				// if ()
				System.out.print("password: ");
				String password = input.nextLine();
				// start session

				User currUser = dao.checkLogin(username, password);
				if (currUser != null && !currUser.getStatus().equals( "super")) {
					do {
						System.out.println("\nWelcome " + currUser.getUsername() + " these are your options:");
						System.out.println("VIEW your accounts.");
						System.out.println("ADD money to an account.");
						System.out.println("WITHDRAW money from an account.");
						System.out.println("CREATE a new account.");
						System.out.println("DELETE an account.");
						System.out.println("LOG OUT");

						in = input.nextLine().toLowerCase();
						// add to account
						// withdraw from account
						// log out
						// create a new account
						// view accounts
						// delete account
						switch (in) {
						case "add":
							System.out.println("Add to which account?");
							String aaccname = input.nextLine();
							System.out.println("How much?");
							int aamt = Math.abs(input.nextInt());
							try {
								dao.updateAccount(aaccname, aamt, currUser);
								System.out.println("Successfully added $" + aamt + " to account " + aaccname);
							} catch (SQLStatementFailedException e) {
								System.out.println(e.getMessage());
							} catch (OverdraftException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "withdraw":
							System.out.println("Withdraw from which account?");
							String waccname = input.nextLine();
							System.out.println("How much?");
							int wamt = Math.abs(input.nextInt());
							try {
								dao.updateAccount(waccname, -wamt, currUser);
								System.out.println("Successfully withdrew $" + wamt + " from account " + waccname);
							} catch (SQLStatementFailedException e) {
								System.out.println(e.getMessage());
							} catch (OverdraftException e) {
								System.out.println(e.getMessage());
							}

							break;
						case "create":
							System.out.println("What would you like to name this account?");
							String caccname = input.nextLine();
							dao.createAccount(caccname, currUser);
							break;
						case "view":
							dao.viewAccounts(currUser);
							break;
						case "delete":
							System.out.println("Delete which account?");
							String daccname = input.nextLine();
							try{
								dao.deleteAccount(daccname, currUser);
								System.out.println("Account " + daccname + " deleted");
							} catch(SQLStatementFailedException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "log out":
							System.out.println("Logged out successfully!");
							break;
						default:
							System.out.println("Unrecognized command");
							break;
						}

					} while (!in.equals("log out"));
				} else if (currUser != null && currUser.getStatus().equals("super")) {
					//A superuser can view, create, update, and delete all users.
					do {
						System.out.println("\nWelcome admin these are your options:");
						System.out.println("VIEW users.");
						System.out.println("CREATE a new user.");
						System.out.println("UPDATE a user.");
						System.out.println("DELETE a user.");
						System.out.println("LOG OUT");

						in = input.nextLine().toLowerCase();
						
						switch (in) {
						case "view":
							dao.viewUsers();
							break;
						case "create":
							try {
							System.out.println("Please enter the desired username:");
							String dUser = input.nextLine();
							dao.checkUsernameAvail(dUser);

							System.out.println("Please enter the desired password:");
							String dPass = input.nextLine();

							dao.registerUser(dUser, dPass);
							} catch(UsernameTakenException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "update":
							System.out.println("Update which user?");
							String uuser = input.nextLine();
							System.out.println("Change username to? (Leave blank to skip)");
							String newUser = input.nextLine();
							if (newUser.equals(""))
								newUser = uuser;
							System.out.println("Change password to?");
							String newPass = input.nextLine();
							try {
								dao.updateUser(uuser, newUser, newPass);
							} catch(SQLStatementFailedException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "delete":
							System.out.println("Delete which user?");
							String duser = input.nextLine();
							try {
								dao.deleteUser(duser);
								System.out.println("User " + duser + " deleted.");
							} catch (SQLStatementFailedException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "log out":
							System.out.println("Successfully logged out.");
							break;
						default:
							System.out.println("Unrecognized command.");
							break;
						}
					}while(!in.equals("log out"));
				}
				break;
			case "register":
				try {
					System.out.println("Please enter your desired username:");
					String dUser = input.nextLine();
					dao.checkUsernameAvail(dUser);

					System.out.println("Please enter your desired password:");
					String dPass = input.nextLine();

					dao.registerUser(dUser, dPass);

				} catch (UsernameTakenException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				System.out.println("Have a good day!");
				break;
			default:
				System.out.println("Unrecognized command");
				break;
			}

		} while (!in.equals("exit"));
		input.close();

	}

}
