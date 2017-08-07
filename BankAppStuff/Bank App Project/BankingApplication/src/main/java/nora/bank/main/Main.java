package nora.bank.main;

import java.util.List;
import java.util.Scanner;

import nora.bank.dao.*;
import nora.bank.domain.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean keepRunning = true;
		boolean loggedIn = false;
		AppUser user = new AppUser();

		while (keepRunning) {
			printLogOptions();

			// TODO: Handle incorrect input issues here and in the switch statement
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				login(user);
				loggedIn = true;
				break;
			case 2:
				makeNewUser(sc, user.getUser());
				loggedIn = true;
				break;
			case 3:
				System.out.println("Okay! Goodbye! Thank you for using generic banking application TM.");
				keepRunning = false;
				break;
			}

			while (loggedIn) {
				if (user.getUser().getUserType() == User.NORMAL_USER) {
					printNormieOptions();
					int input = getUserOptionInput(sc, user.getUser().getUserType());
					if(input == 1) loggedIn = false;
					handleNormieInput(input);
				} else {
					printSuperUserOptions();
					int input = getUserOptionInput(sc, user.getUser().getUserType());
					if(input == 1) loggedIn = false;
					handleSuperInput(input);
				}

			} // inner while loop

		} // outer while loop

		sc.close();

	}// main



	private static void printLogOptions() {
		System.out.println("What would you like to do?");
		System.out.println("    1) Log in with your username and password.");
		System.out.println("    2) Create a new user account.");
		System.out.println("    3) Exit this application.");

	}

	private static void printNormieOptions() {
		System.out.println("What would you like to do?");
		System.out.println("    1) Logout");
		System.out.println("    2) View your current accounts and balances");
		System.out.println("    3) Make a withdrawal");
		System.out.println("    4) Make a deposit");
		System.out.println("    5) Delete an empty bank account");
		System.out.println("    6) Create a new bank account");
	}

	private static void printSuperUserOptions() {
		System.out.println("What would you like to do?");
		System.out.println("    1) Logout");
		System.out.println("    2) View all users");
		System.out.println("    3) Create a new user");
		System.out.println("    4) Update an existing user");
		System.out.println("    5) Delete a user");
	}

	private static int getUserOptionInput(Scanner sc, int userType) {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
		// TODO: Handle if people try to input something other than an int
		int input = sc.nextInt();

		while (true) {
			if (input == 1) //1 is logout for them both but feels like a redundant check now
				return input;
			else if (userType == User.NORMAL_USER && input > 0 && input < 7)
				return input;
			 else if(input > 0 && input < 6)
			 return input;
			else {
				System.out.println("Please input a proper option.");
				input = sc.nextInt();
			}
		}

	}

	private static void login(AppUser user) {
		Scanner sc = new Scanner(System.in);
		BankDAO bank = new BankDAOImpl();

		do {
			System.out.print("Please enter your username: ");
			String username = sc.nextLine();
			System.out.print("Please enter your password: ");
			String password = sc.nextLine();
			
			if(bank.logUserIn(username, password)) break;
			
		} while (true);
		
		//Closing a scanner in any way before the application is done running will break all further
		//attempts at using a scanner
		//sc.close();
	}

	private static void makeNewUser(Scanner sc, User user) {
		sc = new Scanner(System.in);
		System.out.print("Please enter your new username: ");
		user.setUsername(sc.nextLine());
		
		System.out.print("Please enter your first name: ");
		user.setFirstName(sc.nextLine());
		
		System.out.print("Please enter your last name: ");
		user.setLastName(sc.nextLine());
		
		System.out.print("Please enter your password: ");
		user.setPassword(sc.nextLine());
		
		BankDAO bank = new BankDAOImpl();
		bank.createUser(user);

	}
	
	private static void handleNormieInput(int userOptionInput) {
		BankDAO bank = new BankDAOImpl();
		AppUser appUser = new AppUser();
		User user = appUser.getUser();
		
		//setup variables for use in the switch statement logic.
		int accountNum = 0;
		int amount = 0;
		Scanner sc = new Scanner(System.in);
		List<BankAccount> accounts = bank.getUserAccountsAndBalances(user.getUsername());
		
		switch(userOptionInput) {
		case 1: // logout
			logout();
			break;
		case 2: // View your current accounts and balances
			for(BankAccount account : accounts) {
				System.out.println(account);
			}
			System.out.println("----");
			
			break;
		case 3: // make a withdrawal
			for(BankAccount account : accounts) {
				System.out.println(account);
			}
			
			System.out.println("Which account would you like to withdraw from?");
			accountNum = sc.nextInt();
			
			//Ensure that the account number that the user has entered is actually an account
			//that belongs to this user
			checkUserBankAccountNum(sc, accounts, accountNum);
			
			System.out.println("How much will you be withdrawing? (You may enter a negative number to cancel the transaction)");
			amount = sc.nextInt();
			
			if(amount < 0) return;

			while(!doesUserHaveEnoughFunds(accountNum, accounts, amount)) {
				System.out.println("Sorry, but you do not have enough funds in that account for that withdraw.");
				System.out.println("Please enter an amount to withdraw or enter a negative number to cancel transaction.");
				amount = sc.nextInt();
				
				if(amount < 0) return;
				
			}
			
			bank.withdraw(accountNum, amount);
			
			break;
		case 4: // make a deposit
			for(BankAccount account : accounts) {
				System.out.println(account);
			}
			
			System.out.println("Which account would you like to deposit into?");
			accountNum = sc.nextInt();
			
			//Ensure that the account number that the user has entered is actually an account
			//that belongs to this user
			checkUserBankAccountNum(sc, accounts, accountNum);
			
			System.out.println("How much will you be depositing?");
			amount = sc.nextInt();
			bank.deposit(accountNum, amount);
			
			System.out.println("----");
			break;
		case 5: // delete an empty bank account
			for(BankAccount account : accounts) {
				System.out.println(account);
			}
			
			System.out.println("Which account would you like to delete? (NOTE: the account must be empty)");
			accountNum = sc.nextInt();
			
			//Ensure that the account number that the user has entered is actually an account
			//that belongs to this user
			checkUserBankAccountNum(sc, accounts, accountNum);
			
			while(!isAccountEmpty(accountNum, accounts)) {
				System.out.println("That account is not empty. Enter account number 0 to end this transaction or enter a valid account number.");
				accountNum = sc.nextInt();
				
				if(accountNum == 0) {
					System.out.println("Account deletion canceled.");
					System.out.println("----");
					return;
				}
				
				checkUserBankAccountNum(sc, accounts, accountNum);
			}
			
			bank.deleteBankAccount(accountNum);
			
			System.out.println("The account has been deleted.");
			System.out.println("----");
			
			break;
		case 6: //create a new bank account associated with this user
			bank.createBankAccount(user.getUserID());
			System.out.println("Your new account has been created.");
			System.out.println("To view your new account, select the option to view all your accounts and balances.");
			System.out.println("----");
			break;
		}
		
	}

	private static boolean isAccountEmpty(int accountNum, List<BankAccount> accounts) {
		for(BankAccount account : accounts) {
			if(account.getAccountID() == accountNum && account.getBalance() == 0) return true;
		}
		return false;
	}

	private static boolean doesUserHaveEnoughFunds(int accountNum, List<BankAccount> accounts, int withdraw) {
		for(BankAccount account : accounts) {
			if(account.getAccountID() == accountNum && withdraw <= account.getBalance()) return true;
		}
		return false;
	}

	private static int checkUserBankAccountNum(Scanner sc, List<BankAccount> accounts, int accountNum) {
		while(!isUserBankAccount(accountNum, accounts)) {
			System.out.println("That does not match one of your bank accounts. Please enter a valid account number.");
			accountNum = sc.nextInt();
		}
		
		return accountNum;
	}

	private static boolean isUserBankAccount(int accountNum, List<BankAccount> accounts) {
		for(BankAccount account : accounts) {
			if(account.getAccountID() == accountNum) return true;
		}
		return false;
	}



	private static void handleSuperInput(int userOptionInput) {
		//set up all of the variables to be used in the switch statement
		Scanner sc = new Scanner(System.in);
		BankDAO bank = new BankDAOImpl();
		List<User> users = bank.getAllUsers();
		int accountID = 0;

		//A superuser can view, create, update, and delete all users.
		switch(userOptionInput) {
		case 1: // logout
			logout();
			break;
		case 2: // view users
			for(User user : users) {
				System.out.println(user);
			}
			
			System.out.println("That is all the users.");
			System.out.println("----");
			break;
		case 3: // create a new user
			User newUser = new User();
			makeNewUser(sc, newUser);
			break;
		case 4: // update an existing user
			// implement ability to change user firstName, lastName, or other standard user information
			//First we pick out what user we want to get
			User editUser = chooseUserToEdit(users);
			
			//go through and make changes to the user that you want to make and view them.
			editUser = editChosenUser(editUser);
			
			bank.editExistingUser(editUser);
			
			System.out.println("The account information has been changed");
			// NOT BANK ACCOUNTS
			break;
		case 5: // delete a user
			for(User user : users) {
				System.out.println(user);
			}
			System.out.println("What is the userID of the user account you want to delete? (Note: You are not allowed to delete any super accounts)");
			
			accountID = getUserIDFromSuper(sc, users);
			
			bank.deleteUser(accountID);
			
			System.out.println("The account has been deleted.");
			System.out.println("----");
			
			break;
		}
	}




	private static User editChosenUser(User editUser) {
		User originalUser = new User(editUser);
		boolean isDone = false;
		Scanner sc = new Scanner(System.in);
		String newValue = "";
		
		while(!isDone) {
			System.out.println("What part of the user would you like to alter?");
			System.out.println("    0) You are done editing the user");
			System.out.println("    1) Username");
			System.out.println("    2) Password");
			System.out.println("    3) First Name");
			System.out.println("    4) Last Name");
			
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			sc = new Scanner(System.in);
			
			switch(choice) {
			case 0: //Super User is done making changes to this account
				isDone = true;
				break;
			case 1:// change username
				System.out.print("New username: ");
				newValue = sc.nextLine();
				editUser.setUsername(newValue);
				break;
			case 2: // change password
				System.out.print("New password: ");
				newValue = sc.nextLine();
				editUser.setPassword(newValue);
				break;
			case 3: // change first name
				System.out.print("New first name: ");
				newValue = sc.nextLine();
				editUser.setFirstName(newValue);
				break;
			case 4: // change last name
				System.out.print("New last name: ");
				newValue = sc.nextLine();
				editUser.setLastName(newValue);
				break;
			}
			
			System.out.println("Here is a comparison of your changes so far and the original.");
			
			System.out.println("Original: " + originalUser);
			System.out.println("Changes: " + editUser);
			System.out.println();
		}
		
		return editUser;
		
	}



	private static User chooseUserToEdit(List<User> users) {
		for(User user : users) {
			System.out.println(user);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the userID of the user whose account you want to edit.");
		int userID = getUserIDFromSuper(sc, users);
		
		for(User user : users) {
			if(user.getUserID() == userID) return user;
		}
		
		return null;//will never get called or should not be reachable
	}



	private static int getUserIDFromSuper(Scanner sc, List<User> users) {
		int userID = sc.nextInt();
		while(true) {
			for(User user : users) {
				if(userID == user.getUserID()) {
					if(user.getUserType() == 2) {
						System.out.println("This account is a super user and you are not allowed to alter them.");
						break;
					} else return userID;
				}
			}
			System.out.println("The user ID you have entered is not valid.");
			System.out.println("Please enter a valid user ID.");
			userID = sc.nextInt();	
		}
	}



	private static void logout() {
		AppUser appUser = new AppUser();
		User user = appUser.getUser();
		user.setFirstName(null);
		user.setLastName(null);
		user.setPassword(null);
		user.setUserID(0);
		user.setUsername(null);
		user.setUserType(1);
	}
	
}
