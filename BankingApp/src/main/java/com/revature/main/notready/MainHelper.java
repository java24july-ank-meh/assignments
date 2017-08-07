package com.revature.main.notready;

import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.Transaction;
import com.revature.domain.User;

public class MainHelper {

	public MainHelper() {
		// TODO Auto-generated constructor stub
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
