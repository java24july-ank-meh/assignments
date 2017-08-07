package com.revature.main.notready;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Account;
import com.revature.domain.Transaction;
import com.revature.domain.User;

public class SuperRun {

	public SuperRun() {
		// TODO Auto-generated constructor stub
	}
	public static void superMenu(Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("C");
		selectable.add("V");
		selectable.add("U");
		selectable.add("D");
		selectable.add("T");
		selectable.add("X");

		UserDao uD = new UserDaoImpl();
		TransactionDao tD = new TransactionDaoImpl();

		while (true) {
			action = "";

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Super Menu--");
				System.out.println("Create User Login-				C");
				System.out.println("View Users-						V");
				System.out.println("Update User-					U");
				System.out.println("Delete User-     				D");
				System.out.println("Full Transaction Log-			T");
				System.out.println("Exit program-					X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 4);
			if (action.equals("X")) {
				System.out.println("Exiting from program...");
				selectionToMenu(action,-1);
			} else {
				if (action.equals("C")) {
					User u = new User();

					System.out.println("Please enter in the folowing info");
					System.out.println("User username:");
					u.setUsername(scan.nextLine());
					System.out.println("User password:");
					u.setPassword(scan.nextLine());
					System.out.print("\n User first name: ");
					u.setfName(scan.nextLine());
					System.out.print("\n User last name: ");
					u.setlName(scan.nextLine());

					System.out.println("User Login info added.");

					List<User> ul = new ArrayList<User>();
					ul.add(u);
					MainHelper.printOrView(ul, "view", "userL");

					uD.createUser(u);
				}
				if (action.equals("V")) {
					List<User> list = uD.readAllUsers();
					MainHelper.printOrView(list, "view", "user");
					int selectedAccount = -1;

					while (selectedAccount < 0) {
						System.out.println("Please select an User number from 0 to " + list.size() + ".");
						selectedAccount = scan.nextInt();
					}
					innerViewSuperMenu(list.get(selectedAccount), scan);
				}
				if (action.equals("U")) {
					int selectedAccount = -1;

					while (selectedAccount <= 0) {
						System.out.println("Please input a User id for update.");
						selectedAccount = scan.nextInt();
					}
					User u = uD.readUser(selectedAccount);
					User u2 = new User();
					u2.setUserID(selectedAccount);
					System.out.println("User id is " + selectedAccount);
					System.out.println(
							"If you would like to not update the info, please put a '.', dot/period and go to next one with enter..");
					System.out.print("\n User user name: " + u.getUsername() + ", please enter new user name.");
					u2.setUsername(scan.nextLine());
					System.out.print("\n User password: " + u.getPassword() + ", please enter new password.");
					u2.setPassword(scan.nextLine());
					System.out.print("\n User first name: " + u.getfName() + ", please enter new first name.");
					u2.setfName(scan.nextLine());
					System.out.print("\n User middle name: " + u.getmName() + ", please enter new middle name.");
					u2.setmName(scan.nextLine());
					System.out.print("\n User last name: " + u.getlName() + ", please enter new last name.");
					u2.setlName(scan.nextLine());
					System.out.print("\n User address: " + u.getAddress() + ", please enter new address.");
					u2.setAddress(scan.nextLine());
					System.out.print("\n User city: " + u.getCity() + ", please enter new city.");
					u2.setCity(scan.nextLine());
					System.out.print("\n User state: " + u.getState() + ", please enter new state.");
					u2.setState(scan.nextLine());
					System.out.print("\n User postal code: " + u.getPostalCode() + ", please enter new postal code.");
					u2.setPostalCode(scan.nextLine());

					System.out.println("User info being updated");

					u2 = uD.compileUpdatedValues(u2, u);

					List<User> ul = new ArrayList<User>();
					ul.add(u2);
					MainHelper.printOrView(ul, "view", "user");

					uD.updateUserI(u2);
					uD.updateUserL(u2);
				}
				if (action.equals("D")) {

					System.out.println("Are you sure you want to delete a user? (Y/N)");
					String answer = scan.nextLine();
					if (answer.equals("Y")) {

						int selectedUser = -1;

						while (selectedUser <= 0) {
							System.out.println("Now enter in a user id.");
							selectedUser = scan.nextInt();
						}

						System.out.println("We will now delete the user with id " + selectedUser);
						uD.deleteUser(selectedUser);

					} else if (answer.equals("N")) {
						System.out.println("Okay, we wont delete a user.");
					} else {
						System.out.println("We will take that as no, back to start of menu.");
					}

				} else {
					System.out.println("Reading all transactions");
					List<Transaction> list = tD.readAllTransaction();
					MainHelper.printOrView(list, "view", "account");

				}
			}
		}

	}

	public static void innerViewSuperMenu(User u, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("U");
		selectable.add("D");
		selectable.add("GB");
		selectable.add("X");

		while (true) {
			action = "";

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Account Statements--");
				System.out.println("Update User-				U");
				System.out.println("Delete User-				D");
				System.out.println("Go back a menu-				GB");
				System.out.println("Exit program-				X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 5);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				superMenu(scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				exitApp();
			} else {
				if (action.equals("U")) {

				} else {

				}
			}



		}
	}

	/*
	 * Directory -1 exit
	 * 
	 * 0 SuperUser main 0.1 Create User Login 0.2 View Users 0.21 Select a Current
	 * user 0.211 Update user 0.212 Delete user 0.22 See Deleted Users 0.221 Restore
	 * a user with new id tho... 0.3 Update User 0.4 Delete User 0.5 Read All User
	 * Transactions
	 * 
	 * 1* 1.0 User Main
	 * 
	 * 2* 2.0 account statements 2.1 View 2.2 Print
	 * 
	 * 3* 3.0 user info 3.1 See info 3.2 Update info 3.3 Print
	 * 
	 * 4* 4.0 accounts 4.1 Create 4.2 Select an Account 4.21 Deposit 4.22 Withdraw
	 * 4.23 Delete
	 * 
	 * 5* 5.0 transaction log 5.1 ReadAll 5.11 View 5.12 Print 5.2 Select an account
	 * 5.21 View 5.22 Print
	 */
	public static void selectionToMenu(String selection, double in) {
		String selectionName = "";

		if (in == -1 || selection.equals("X") || selection.equals("-1")) {
			System.out.println("Time to exit, have a good day. Come back again real soon!");
			exitApp();
		} else if (in >= 0 && in < 1) {
			if (in == 0) {// superuser main menu
				if (selection.equals("C")) {
					selectionName = "Create User";
				} else if (selection.equals("V")) {
					selectionName = "View Users";
				} else if (selection.equals("U")) {
					selectionName = "Update User";
				} else if (selection.equals("D")) {
					selectionName = "Delete User";
				} else if (selection.equals("T")) {
					selectionName = "Transaction Log(All Users)";
				}
			}
			if (in == 0.2) {// view users menu
				if (selection.equals("S")) {
					selectionName = "Select a User";
				} else if (selection.equals("V")) {
					selectionName = "View Deleted Users";
				}
			}

			if (in == 0.21) {// select a current user menu
				if (selection.equals("U")) {
					selectionName = "Update User";
				} else if (selection.equals("D")) {
					selectionName = "Delete Users";
				}
			}

			if (in == 0.22) {// deleted users menu
				if (selection.equals("R")) {
					selectionName = "Restore User";
				}
			}

			System.out.println("Selected was " + selection + ": " + selectionName);
		}
		System.out.println("Selected was " + selection + ": " + selectionName + " from menu " + in);

	}

	public static void exitApp() {
		System.out.println("Thank you for for being a superuser, hope everything worked wonderfully.");
		System.exit(0);
	}

}
