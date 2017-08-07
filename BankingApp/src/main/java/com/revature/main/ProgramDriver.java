package com.revature.main;

import java.util.*;
import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.exceptions.*;

public class ProgramDriver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		LoginDaoImpl lD = new LoginDaoImpl();

		boolean loginValidation = false;
		boolean completedTask = false;
		boolean validUN = false;
		User mainUser = null;
		
		int counterLogin = 1;

		System.out.println("Hello, Welcome to the Banking App. Made to better your banking experience.");
		
		while (!loginValidation && counterLogin <= 5) {

			System.out.println("Please enter in your username or user id.");
			String userInput = scan.nextLine();

			validUN = lD.isValidLoginFirst(userInput);
			System.out.println("valid Num " + validUN);

			if (validUN) {
				System.out.println("Now please enter your password.");
				String userPassword = scan.nextLine();

				if (lD.correctPassword(userInput, userPassword)) {
					loginValidation = true;
					mainUser = lD.readLoginByUN(userInput);
				} else {
					try {
						throw new IncorrectPassword();
					} catch (IncorrectPassword e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				System.out.println("Inputs are " + userInput + " " + userPassword + " counter at " + counterLogin);
			} else {

				try {
					throw new UserNameNotFound();
				} catch (UserNameNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("Would you like to register up? (Y/N)");
					String register = scan.nextLine().toUpperCase();
					if (register.equals("Y")) {
						registration(lD, scan);
					}
				}

			}

			counterLogin++;
		}

		System.out.println("You have correctly logged in.. \n");
		
		while (!completedTask) {
			if(mainUser.equals("0")) {
				superMenu(scan);
			}else {
				theMeatOfTheMenus(decidingMenu(scan), mainUser, scan);
			}

		}

	}

	public static void registration(LoginDaoImpl lD, Scanner scan) {
		boolean valid = false;
		boolean unique = false;
		String f = "";
		String l = "";
		String n = "";
		String p = "";

		while (!valid) {
			System.out.println("First name: ");
			f = scan.nextLine();
			System.out.println("Last name: ");
			l = scan.nextLine();
			
			valid = lD.validateUserByName(f, l);
			System.out.println(f + " " + l+" "+valid);
			
			if (!valid) {
				System.out.println("Want to exit app? (Y/N)");
				String exitAnswer = scan.nextLine().toUpperCase();
				if (exitAnswer.equals("Y")) {
					exitApp("new user unfinished");
				}
			}
		}

		valid = false;

		while (!valid) {
			System.out.println("Username: (can be anything, but shorter than 10 characters)");
			n = scan.nextLine();
			
			if (n.toCharArray().length <= 10) {
				
				unique = lD.uniqueUsername(n);
				System.out.println(n+" "+unique);
				
				if (unique) {
					System.out.println("Password: (can be anything, but shorter than 15 characters)(default is password");
					p =  scan.nextLine();
					System.out.println("New Password: "+p);
					while (p.isEmpty()) {
						p = "password";
					}
					System.out.println("New Password: "+p);
					valid = true;
				}
			}

		}
		System.out.println("User: "+f+" "+l+", username and password= "+n+" and "+p);
		User u = new User(n, p, f, l);
		lD.createLogin(u);

		System.out.println("You can add more info in the User Info menu in the app.");
		System.out.println("Please wait a few mins before trying to login. ");
		exitApp("new user");
	}

	// ----------------------------------------------------------------------------------------
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
		} else if (in >= 1) {

			if (in == 1) {// main menu
				if (selection.equals("S")) {
					selectionName = "Account Statements";
				} else if (selection.equals("I")) {
					selectionName = "User Info";
				} else if (selection.equals("A")) {
					selectionName = "Accounts";
				} else if (selection.equals("T")) {
					selectionName = "Transaction Logs";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}

			}

			if (in == 2) {// account statements menu
				if (selection.equals("V")) {
					selectionName = "View list of Accounts & Balances";
				} else if (selection.equals("P")) {
					selectionName = "Print list of Accounts & Balances";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			} else if (in == 3) {// user info menu
				if (selection.equals("V")) {
					selectionName = "View Info";
				} else if (selection.equals("U")) {
					selectionName = "Update Info";
				} else if (selection.equals("P")) {
					selectionName = "Print Info";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			} else if (in == 4) {// accounts menu
				if (selection.equals("C")) {
					selectionName = "Create Account";
				} else if (selection.equals("S")) {
					selectionName = "Select an Account";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			} else if (in == 5) {// transaction logs menu
				if (selection.equals("V")) {
					selectionName = "View All Transactions";
				} else if (selection.equals("S")) {
					selectionName = "Select one Account";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			}

			if (in == 4.2) {
				if (selection.equals("D")) {
					selectionName = "Deposit";
				} else if (selection.equals("W")) {
					selectionName = "Withdraw";
				} else if (selection.equals("N")) {
					selectionName = "Delete Account";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			}

			if (in == 5.2) {
				if (selection.equals("V")) {
					selectionName = "View Account's Transaction Log";
				} else if (selection.equals("P")) {
					selectionName = "Print Account's Transaction Log";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			}

			if (in == 5.1) {
				if (selection.equals("V")) {
					selectionName = "View All Accounts' Transaction Log";
				} else if (selection.equals("P")) {
					selectionName = "Print All Accounts' Transaction Log";
				} else if (selection.equals("GB")) {
					selectionName = "Go back a menu";
				} else if (selection.equals("X")) {
					selectionName = "Exit App";
				}
			}

			System.out.println("Selected was " + selection + ": " + selectionName + " from menu " + in);

		}

	}

	// ----------------------------------------------------------------------------
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
				System.out.println("Create User Login-					C");
				System.out.println("View Users-						V");
				System.out.println("Update User-					U");
				System.out.println("Delete User-     				D");
				System.out.println("Full Transaction Log-				T");
				System.out.println("Exit program-					X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 4);
			if (action.equals("X")) {
				System.out.println("Exiting from program...");
				exitApp("");
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
					printOrView(ul, "view", "userL");

					uD.createUser(u);
				}
				if (action.equals("V")) {
					List<User> list = uD.readAllUsers();
					printOrView(list, "view", "user");
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
					printOrView(ul, "view", "user");

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
					printOrView(list, "view", "account");

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
				goBack(0, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("U")) {

				} else {

				}
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------
	public static String decidingMenu(Scanner scan) {
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("S");
		selectable.add("I");
		selectable.add("A");
		selectable.add("T");
		selectable.add("X");

		System.out.println("Main menu--");
		// can go back and put these into a map or arraylist and check to see if inputed
		// key is contained within, also or could change inputs into a number
		System.out.println("Account Statements-		S");
		System.out.println("User Info-			I");
		System.out.println("Accounts-			A");
		System.out.println("Transaction Log-   		T");
		System.out.println("Exit-				X");
		System.out.println("Please enter in selected menu key.");

		String selection = scan.nextLine();
		selection = selection.toUpperCase();

		System.out.println("selection " + selection);

		while (!selectable.contains(selection)) {
			System.out.println("Sorry, please reenter in a selected menu key.");
			selection = scan.nextLine();
			selection = selection.toUpperCase();
			System.out.println("selection " + selection);
		}

		return selection;

	}

	// -----------------------------------------------------------------------------
	public static void theMeatOfTheMenus(String s, User u, Scanner scan) {
		if (s.equals("S")) {
			statementsMenu(u, scan);
		} else if (s.equals("I")) {
			infoMenu(u, scan);
		} else if (s.equals("A")) {
			accountsMenu(u, scan);
		} else if (s.equals("T")) {
			transactionsMenu(u, scan);
		}
		selectionToMenu(s, 1);
	}

	public static void statementsMenu(User u, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("V");
		selectable.add("P");
		selectable.add("GB");
		selectable.add("X");

		UserDao uD = new UserDaoImpl();

		while (true) {
			action = "";
			u = uD.readUser(u.getUserID());

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Account Statements--");
				System.out.println("View Accounts with Balances in a list-		V");
				System.out.println("Print Accounts with Balances in a list-		P");
				System.out.println("Go back a menu-								GB");
				System.out.println("Exit program-								X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 2);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(1, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("V")) {
					printOrView(u.getAccounts(), "view", "account");
				} else {
					printOrView(u.getAccounts(), "print", "account");
				}
			}
		}
	}

	public static void infoMenu(User u, Scanner scan) {
		System.out.println();
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("V");
		selectable.add("U");
		selectable.add("P");
		selectable.add("GB");
		selectable.add("X");

		UserDao uD = new UserDaoImpl();

		while (true) {
			String action = "";

			while (!selectable.contains(action)) {

				System.out.println("User Info--");
				System.out.println("View user info-		V");
				System.out.println("Update user info-   U");
				System.out.println("Print user info-	P");
				System.out.println("Go back a menu-		GB");
				System.out.println("Exit program-		X");

				action = scan.nextLine();
				action = action.toUpperCase();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 3);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(1, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("V")) {
					List<User> ul = new ArrayList<User>();
					ul.add(u);
					printOrView(ul, "view", "user");
				} else if (action.equals("U")) {
					User u2 = new User();
					UserDao ud = new UserDaoImpl();

					System.out.println("User id is" + u.getUserID());
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
					printOrView(ul, "view", "user");

					ud.updateUserI(u2);
					ud.updateUserL(u2);
				} else if (action.equals("P")) {
					List<User> ul = new ArrayList<User>();
					ul.add(u);
					printOrView(ul, "print", "user");
				}
				u = uD.readUser(u.getUserID());
			}
		}
	}

	public static void accountsMenu(User u, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("C");
		selectable.add("S");
		selectable.add("GB");
		selectable.add("X");

		AccountDao aD = new AccountDaoImpl();

		while (true) {
			action = "";

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Accounts--");
				System.out.println("Create account-								C");
				System.out.println("Select Account-								S");
				System.out.println("Go back a menu-								GB");
				System.out.println("Exit program-								X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 4);
			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(1, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("C")) {
					Account a = new Account();

					System.out.println(
							"If you would not like to enter in a field please put a '.', dot/period and go to next one with enter..");
					a.setU(u);
					a.setUserID(u.getUserID());
					System.out.print("\n Please enter an Account name.");
					a.setAccountName(scan.nextLine());
					System.out.println(
							"Balance will start off at 0, please go to the deposit screen to deposit into this account.");

					aD.createAccount(a);
				} else {
					List<Account> list = aD.readAllAccount(u.getUserID());
					printOrView(list, "view", "account");
					int selectedAccount = -1;

					while (selectedAccount < 0) {
						System.out.println("Please select an Account number from 0 to " + list.size() + ".");
						selectedAccount = scan.nextInt();
					}
					innerAccountMenu(list.get(selectedAccount), scan);
				}
			}
		}
	}

	public static void innerAccountMenu(Account a, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("D");
		selectable.add("W");
		selectable.add("N");
		selectable.add("GB");
		selectable.add("X");

		AccountDao aD = new AccountDaoImpl();

		while (true) {
			// just to reupdate the account
			action = "";
			a = aD.readAccount(a.getAccountID());

			while (!selectable.contains(action)) {
				System.out.println("Account name: " + a.getAccountName() + ", Balance: " + a.getBalance());
				System.out.println("\n Please enter input correctly..");
				System.out.println("Accounts--");
				System.out.println("Deposit into account-						D");
				System.out.println("Withdraw from account-						W");
				System.out.println("Delete Account-								N");
				System.out.println("Go back a menu-								GB");
				System.out.println("Exit program-								X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 4.2);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(4, a.getU(), scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, a.getU(), scan);
			} else {
				if (action.equals("D")) {
					double depositAmt = -1.0;
					while (depositAmt < 0) {
						System.out.print("Please enter the Deposit amount.");
						depositAmt = scan.nextDouble();
					}
					aD.depositMoney(a, depositAmt);

					System.out.println(
							"Balance will start off at 0, please go to the deposit screen to deposit into this account.");
				} else if (action.equals("W")) {
					double withdrawAmt = -1.0;
					while (withdrawAmt < 0 && withdrawAmt >= a.getBalance()) {
						System.out.print("Please enter the Deposit amount.");
						withdrawAmt = scan.nextDouble();
					}
					aD.depositMoney(a, withdrawAmt);

					System.out.println(
							"Balance will start off at 0, please go to the deposit screen to deposit into this account.");
				} else {

					System.out.println("Are you sure you want to delete the account? (Y/N)");
					String answer = scan.nextLine();
					if (a.getBalance() == 0 && answer.equals("Y")) {
						System.out.println("Balance is $0.00, Deleting account.");
						aD.deleteAccount(a);
					} else if (answer.equals("N")) {
						System.out.println("Okay, we wont delete account.");
					} else {
						System.out.println(
								"Sorry balance is more than $0.00. Please empty account by withdrawing all money before deleting account.");
					}
				}
			}
		}
	}

	public static void transactionsMenu(User u, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("V");
		selectable.add("S");
		selectable.add("GB");
		selectable.add("X");

		UserDao uD = new UserDaoImpl();
		AccountDao aD = new AccountDaoImpl();

		while (true) {
			action = "";
			u = uD.readUser(u.getUserID());

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Transactions--");
				System.out.println("View all transactions-						V");
				System.out.println("Select an Account to read from-				S");
				System.out.println("Go back a menu-								GB");
				System.out.println("Exit program-								X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 5);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(1, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("V")) {
					printOrView(u.getAccounts(), "view", "transactionA");
				} else {
					List<Account> list = aD.readAllAccount(u.getUserID());
					printOrView(list, "view", "account");
					int selectedAccount = -1;

					while (selectedAccount < 0) {
						System.out.println("Please select an Account number from 0 to " + list.size() + ".");
						selectedAccount = scan.nextInt();
					}
					innerTransactionsMenu(u, list.get(selectedAccount), scan);
				}
			}
		}

	}

	public static void innerTransactionsMenu(User u, Account a, Scanner scan) {
		String action = "";
		ArrayList<String> selectable = new ArrayList<String>();
		selectable.add("V");
		selectable.add("P");
		selectable.add("GB");
		selectable.add("X");

		while (true) {
			action = "";

			while (!selectable.contains(action)) {
				System.out.println("Please enter input correctly..");
				System.out.println("Account Statements--");
				System.out.println("View Transactions-				V");
				System.out.println("Print Transactions-				P");
				System.out.println("Go back a menu-					GB");
				System.out.println("Exit program-					X");

				action = scan.nextLine();
				System.out.println("Input is " + action);
			}

			selectionToMenu(action, 5);

			if (action.equals("GB")) {
				System.out.println("Going back a menu..");
				goBack(1, u, scan);
			} else if (action.equals("X")) {
				System.out.println("Exiting from program...");
				goBack(-1, u, scan);
			} else {
				if (action.equals("V")) {
					printOrView(a.setTransactionsFromDB(), "view", "transaction");
				} else {
					printOrView(a.setTransactionsFromDB(), "print", "transaction");
				}
			}
		}
	}

	// ---------------------------------------------------

	public static void goBack(double i, User u, Scanner s) {
		/*
		 * i will describe which menu u r going back tooo i== -1 exit i== 0 super menu
		 * i== 1 main i== 2 Account Statements i== 3 User Info i== 4 Accounts i== 5
		 * Transactions
		 */
		if (i == -1) {
			exitApp(u);
		} else if (i == 0) {
			superMenu(s);
		} else if (i == 1) {
			decidingMenu(s);
		} else if (i == 2) {
			statementsMenu(u, s);
		} else if (i == 3) {
			infoMenu(u, s);
		} else if (i == 4) {
			accountsMenu(u, s);
		} else if (i == 5) {
			transactionsMenu(u, s);
		}

	}

	/*
	 * exitApp
	 * 
	 * this will help to exit the program so that none of the other methods, just
	 * call this
	 * 
	 * it will jump past the main and close out the program
	 * 
	 */
	public static void exitApp(String s) {
		if(s.equals("new user")){
			System.out.println("Thank you for for registering.");
		}else if(s.equals("new user unfinished")){
			System.out.println("If you would like to register than please try again. Goodbye.");
		}else {
			System.out.println("Thank you for for being a superuser, hope everything worked wonderfully.");
		}
		System.exit(0);
	}

	public static void exitApp(User u) {
		System.out.println(
				"Thank you for using this app, have a good day " + u.getfName() + " " + u.getlName().charAt(0) + ".");
		System.exit(0);
	}

	// --------------------------------------------------

	public static void printOrView(List l, String s, String type) {

		if (type.equals("user")) {
			if (s.equals("print")) {

			} else if (s.equals("view")) {
				for (Object t : l) {
					User temp = (User) t;
					System.out.println("User id is" + temp.getUserID());
					System.out.print("\n User first name: " + temp.getfName());
					System.out.print("\n User middle name: " + temp.getmName());
					System.out.print("\n User last name: " + temp.getlName());
					System.out.print("\n User address: " + temp.getAddress());
					System.out.print("\n User city: " + temp.getCity());
					System.out.print("\n User state: " + temp.getState());
					System.out.print("\n User postal code: " + temp.getPostalCode());
				}
			}
		} else if (type.equals("userL")) {
			if (s.equals("print")) {

			} else if (s.equals("view")) {
				for (Object t : l) {
					User temp = (User) t;
					System.out.println("\n User username: " + temp.getUsername());
					System.out.print("\n User passworde: " + temp.getPassword());
					System.out.print("\n User first name: " + temp.getfName());
					System.out.print("\n User last name: " + temp.getlName());
				}
			} else if (type.equals("account")) {
				if (s.equals("print")) {

				} else if (s.equals("view")) {
					int counter = 0;
					for (Object t : l) {
						Account temp = (Account) t;
						System.out.println("Account id: " + temp.getAccountID());
						System.out.print("\t Account name: " + temp.getAccountName());
						System.out.print("\t Account balance: " + temp.getBalance());
						System.out.print("\t -" + counter);
						counter++;
					}

				}
			} else if (type.equals("transaction")) {// multiple transactions from list

				if (s.equals("print")) {

				} else if (s.equals("view")) {
					for (Object t : l) {
						Transaction temp = (Transaction) t;
						System.out.println("Transaction id: " + temp.getTransactionId());
						System.out.print("\n Account id: " + temp.getIdOfAccount());
						System.out.print("\n Transaction amount: " + temp.getAmountOfTransaction());
					}
				}
			} else if (type.equals("transactionA")) {// multiple transactions from accounts list

				List<Transaction> trL = new ArrayList<Transaction>();

				for (Object t : l) {
					Account temp = (Account) t;
					trL.addAll(temp.setTransactionsFromDB());
				}

				if (s.equals("print")) {

				} else if (s.equals("view")) {
					for (Object t : trL) {
						Transaction temp = (Transaction) t;
						System.out.println("Transaction id: " + temp.getTransactionId());
						System.out.print("\n Account id: " + temp.getIdOfAccount());
						System.out.print("\n Transaction amount: " + temp.getAmountOfTransaction());
					}
				}
			} else if (type.equals("transactionU")) {// multiple transactions from user list

				List<Account> aL = new ArrayList<Account>();
				for (Object t : l) {
					User temp = (User) t;
					aL.addAll(temp.setAccountsFromDB());
				}

				List<Transaction> trL = new ArrayList<Transaction>();
				for (Object t : aL) {
					Account temp = (Account) t;
					trL.addAll(temp.setTransactionsFromDB());
				}

				if (s.equals("print")) {

				} else if (s.equals("view")) {
					for (Object t : trL) {
						Transaction temp = (Transaction) t;
						System.out.println("Transaction id: " + temp.getTransactionId());
						System.out.print("\t Account id: " + temp.getIdOfAccount());
						System.out.print("\t Transaction amount: " + temp.getAmountOfTransaction());
					}
				}
			}

		}
	}
}