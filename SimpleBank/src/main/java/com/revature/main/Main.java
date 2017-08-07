package com.revature.main;

import java.util.Scanner;
import com.revature.domain.*;
import com.revature.dao.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";

		boolean running = true;

		while (running) {
			System.out.println("Welcome to the Simple Bank Application!");
			System.out.println("To start select an option by tpying in: ");
			System.out.println("\t1.) Login: login to an account");
			System.out.println("\t2.) Register: register an account");
			System.out.println("\t3.) Quit: exit out of application");

			str = sc.nextLine();
			str.toLowerCase();

			if (str.equals("login") || str.equals("1")) {
				String username = "";
				char[] password;
				System.out.println("Please enter user name: ");
				username = sc.nextLine();
				System.out.println("Please enter password: ");
				password = sc.nextLine().toCharArray();

				LoginDAO l = new LoginDAOImpl();
				Login log = new Login(username, password);

				if (log.getUserName().equals("admin") && String.copyValueOf(log.getPassword()).equals("admin")) {
					System.out.println("Successfully logged in as admin!");
					System.out.println();

					String option;
					superUserDAOImpl super_user = new superUserDAOImpl();

					while (running) {
						System.out.println("Enter an option: ");
						System.out.println("\t1.) View: view all accounts");
						System.out.println("\t2.) Create: create an account");
						System.out.println("\t3.) Delete: delete an account");
						System.out.println("\t4.) Update: update an account");
						System.out.println("\t5.) Logout");

						option = sc.nextLine();
						if (option.equalsIgnoreCase("view") || option.equalsIgnoreCase("1")) {
							super_user.readAllUsers();
							System.out.println();
							continue;
						} else if (option.equalsIgnoreCase("create") || option.equalsIgnoreCase("2")) {
							int acc_number = (int) (Math.random() * 1000000);
							String last;
							String first;
							String created_username;
							char[] created_password;
							System.out.print("Enter last name for new account: ");
							last = sc.nextLine();
							System.out.println();
							System.out.print("Enter first name for new account: ");
							first = sc.nextLine();
							System.out.println();
							System.out.print("Enter username for new account: ");
							created_username = sc.nextLine();
							System.out.println();
							System.out.print("Enter password for new account: ");
							created_password = sc.nextLine().toCharArray();

							Login new_login = new Login(created_username, created_password);
							User new_user = new User(new_login, acc_number, last, first, 0);
							if (super_user.createUser(new_user)) {
								System.out.println("Successfully created new user!");
								System.out.println();
								continue;
							} else {
								System.out.println("Something went wrong, try again");
								continue;
							}
						} else if (option.equalsIgnoreCase("delete") || option.equalsIgnoreCase("3")) {
							System.out.print("Enter account ID to delete: ");
							int del_id = sc.nextInt();
							if (super_user.deleteUser(del_id)) {
								System.out.println("Account succefully deletd");
								System.out.println();
								continue;
							} else {
								System.out.println("Something went wrong, try agian.");
								System.out.println();
								continue;
							}
						} else if (option.equalsIgnoreCase("update") || option.equalsIgnoreCase("4")) {
							int upd_id;
							String upd_str;
							System.out.print("Enter an Account ID to update: ");
							upd_id = sc.nextInt();
							boolean update = true;
							while (update) {
								System.out.println("Enter an option to update: ");
								System.out.println("\t1.) last name: update last name");
								System.out.println("\t2.) first name: update first name");
								System.out.println("\t3.) balance: update balance name");
								System.out.println("\t4.) username: update username name");
								System.out.println("\t5.) password: update password name");
								System.out.println("\t6.) stop: stop updating");
								upd_str = sc.nextLine();
								if (upd_str.equalsIgnoreCase("last name") || upd_str.equalsIgnoreCase("last")
										|| upd_str.equalsIgnoreCase("1")) {
									System.out.println("Enter new last name: ");
									String new_last = sc.nextLine();
									if (super_user.updateLastName(upd_id, new_last)) {
										System.out.println("Update successful!");
										continue;
									} else {
										System.out.println("Something went wrong, try again.");
										continue;
									}
								} else if (upd_str.equalsIgnoreCase("first name") || upd_str.equalsIgnoreCase("first")
										|| upd_str.equalsIgnoreCase("2")) {
									System.out.println("Enter new first name: ");
									String new_first = sc.nextLine();
									if (super_user.updateFirstName(upd_id, new_first)) {
										System.out.println("Update successful!");
										continue;
									} else {
										System.out.println("Something went wrong, try again.");
										continue;
									}
								} else if (upd_str.equalsIgnoreCase("balance") || upd_str.equalsIgnoreCase("3")) {
									System.out.println("Enter new balance: ");
									int new_bal = sc.nextInt();
									if (super_user.updateBalance(upd_id, new_bal)) {
										System.out.println("Update successful!");
										continue;
									} else {
										System.out.println("Something went wrong, try again.");
										continue;
									}
								} else if (upd_str.equalsIgnoreCase("username") || upd_str.equalsIgnoreCase("4")) {
									System.out.println("Enter new username: ");
									String new_usrnm = sc.nextLine();
									if (super_user.updateUserName(upd_id, new_usrnm)) {
										System.out.println("Update successful!");
										continue;
									} else {
										System.out.println("Something went wrong, try again.");
										continue;
									}
								} else if (upd_str.equalsIgnoreCase("password") || upd_str.equalsIgnoreCase("5")) {
									System.out.println("Enter new password: ");
									char[] new_passw = sc.nextLine().toCharArray();
									if (super_user.updatePassword(upd_id, new_passw)) {
										System.out.println("Update successful!");
										continue;
									} else {
										System.out.println("Something went wrong, try again.");
										continue;
									}
								} else if (upd_str.equalsIgnoreCase("stop") || upd_str.equalsIgnoreCase("6")) {
									update = false;
									continue;
								} else {
									System.out.println("Invalid option, please try again");
									continue;
								}
							}
						} else if (option.equalsIgnoreCase("logout") || option.equalsIgnoreCase("3")) {
							System.out.println("Now logging out.");
							System.out.println();
							running = false;
							continue;
						} else {
							System.out.println("Please enter a valid option.");
							continue;
						}
					}
				}

				else {
					RegularUserDAOImpl reg_user;
					User curr;
					if (l.login(log)) {
						System.out.println("Successfully logged in as " + log.getUserName() + "!");
						System.out.println();
						curr = l.getUserInfo(log);

						String option;
						System.out.println("Enter an option: ");
						System.out.println("\t1.) deposit: Deposit money");
						System.out.println("\t2.) withdraw: Withdraw money");
						System.out.println("\t3.) transaction: Get transaction history");
						System.out.println("\t4.) Logout");

						while (running) {
							option = sc.nextLine();
							reg_user = new RegularUserDAOImpl();
							if (option.equalsIgnoreCase("deposit") || option.equalsIgnoreCase("1")) {
								int id;
								int amount;
								System.out.println();
								System.out.print("Enter amount you wish to deposit into this account: ");
								amount = sc.nextInt();
								System.out.println();
								if (reg_user.deposit(curr.getAccountID(), curr.getAccountNumber(), amount)) {
									System.out.println("Successfully deposited money into account");
									continue;
								} else {
									System.out.println("Something went wrong, please try again.");
									continue;
								}
							} else if (option.equalsIgnoreCase("withdraw") || option.equalsIgnoreCase("2")) {

								running = false;
							} else if (option.equalsIgnoreCase("transaction") || option.equalsIgnoreCase("3")) {
								if(reg_user.getTransactionHistory(curr.getAccountNumber())) {
									continue;
								}
								else {
									System.out.println("Something went wrong, please try agian");
									continue;
								}
							} else if (option.equalsIgnoreCase("logout") || option.equalsIgnoreCase("4")) {
								System.out.println("Now logging out.");
								System.out.println();
								running = false;
								continue;
							} else {
								System.out.println("Please enter a valid option.");
								continue;
							}
						}
					} else {
						System.out.println("Sorry, could not find this account. Try agian.");
						System.out.println();
					}
				}
				running = true;
				continue;
			} else if (str.equals("register") || str.equals("2")) {
				RegularUserDAOImpl reg_user = new RegularUserDAOImpl();
				int acc_number = (int) (Math.random() * 1000000);
				String last;
				String first;
				String created_username;
				char[] created_password;
				System.out.print("Enter last name for new account: ");
				last = sc.nextLine();
				System.out.println();
				System.out.print("Enter first name for new account: ");
				first = sc.nextLine();
				System.out.println();
				System.out.print("Enter username for new account: ");
				created_username = sc.nextLine();
				System.out.println();
				System.out.print("Enter password for new account: ");
				created_password = sc.nextLine().toCharArray();

				Login new_login = new Login(created_username, created_password);
				User new_user = new User(new_login, acc_number, last, first, 0);
				if (reg_user.createUser(new_user)) {
					System.out.println("Successfully registered new user!");
					System.out.println();
					continue;
				} else {
					System.out.println("Something went wrong, try again");
					continue;
				}

			} else if (str.equals("quit") || str.equals("q") || str.equals("3")) {
				System.out.println("Thank you for using the Simple Bank Application, good-bye.");
				running = false;
			} else {
				System.out.println("Please enter a valid option.");
				running = true;
			}
		}
	}

}
