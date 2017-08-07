package com.revature.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.*;
import com.revature.domain.AccountUser;

public class Main {

	public static void accountOperation(Scanner scan, AccountUser currentUser, BankDAO dao, boolean exit) {
		int s = 0;
		boolean logout = false;

		while (logout == false && exit == false) {

			while (s != 1 && s != 2 && s != 3 && s != 4) {
				
				System.out.println("________________________________________");
				System.out.println("Press 1 to withdraw from account \n" + "Press 2 to deposit into bank account\n"
						+ "Press 3 to log out\n" + "Press 4 to exit");
				System.out.println("________________________________________");
				
				s = scan.nextInt();
				scan.nextLine();
			}
			int amt = 0;
			int id = 0;

			switch (s) {

			// withdraw
			case 1:
				System.out.println("Please enter id of account to withdraw from: ");
				id = scan.nextInt();
				scan.nextLine();
				System.out.println("Please enter amount of money to withdraw: ");
				id = scan.nextInt();
				scan.nextLine();
				dao.withdraw(id, amt);
				break;

			// deposit
			case 2:
				System.out.println("Please enter id of account to deposit into: ");
				id = scan.nextInt();
				scan.nextLine();
				System.out.println("Please enter amount of money to deposit: ");
				id = scan.nextInt();
				scan.nextLine();
				dao.deposit(id, amt);
				break;

			// logout
			case 3:
				logout = true;
				break;
			// exit
			case 4:
				exit = true;
				break;
			}
		}
	}

	public static boolean userSwitch(Scanner scan, AccountUser currentUser, BankDAO dao, boolean exit) {

		boolean logout = false;

		while (logout == false && exit == false) {

			System.out.println("Logged in as: " + currentUser.getUserName() + "\nSuperUser : " + currentUser.isSuper());
			int s = 0;
			while (s != 1 && s != 2 && s != 3 && s != 4 && s!=5) {
				
				System.out.println("________________________________________");
				System.out.println("Press 1 to view bank accounts \n" + "Press 2 to create new bank account\n"
						+ "Press 3 to delete a bank account\n" + "Press 4 to log out\n" + "Press 5 to exit");
				System.out.println("________________________________________");
				
				s = scan.nextInt();
				scan.nextLine();
			}
			
			switch (s) {
			// view accounts
			case 1:
				dao.viewBaccounts(currentUser);
				break;
			// new account
			case 2:
				int amt = 0;
				System.out.println("Creating new bank account \nPlease enter initial account deposit: ");
				amt = scan.nextInt();
				scan.nextLine();
				dao.newBaccount(amt, currentUser.getId());
				break;
			// delete account
			case 3:
				int id = 0;
				System.out.println("Deleting bank account \nPlease enter id of account to delete: ");
				id = scan.nextInt();
				scan.nextLine();
				dao.deleteBaccount(id);
				break;
			// log out
			case 4:
				logout = true;
				break;
			// exit
			case 5:
				exit = true;
				break;
			}
		}
		System.out.println("LEAVING USERSWITCHS");
		return exit;
	}

	public static boolean superSwitch(Scanner scan, AccountUser currentUser, BankDAO dao, boolean exit) {
		/* A superuser can view, create, update, and delete all users. */
		boolean logout = false;

		while (logout == false && exit == false) {

			System.out.println("Logged in as: " + currentUser.getUserName() + "\nSuperUser : " + currentUser.isSuper());
			int s = 0;
			while (s != 1 && s != 2 && s != 3 && s != 4 && s != 5 && s != 6 && s != 7 && s != 8 && s != 9) {
				
				System.out.println("________________________________________");
				System.out.println("Press 1 to view bank accounts \n" + "Press 2 to create new bank account\n"
						+ "Press 3 to delete a bank account\n" + "Press 4 to log out\n" + "Press 5 to view all users\n"
						+ "Press 6 to delete all users\n" + "Press 7 to update user\n" + "Press 8 to create new user\n"
						+ "Press 9 to exit");
				System.out.println("________________________________________");
				
				s = scan.nextInt();
				scan.nextLine();
			}

			int id = 0;
			int amt = 0;
			String name;
			String pass;

			switch (s) {

			// view accounts
			case 1:
				dao.viewBaccounts(currentUser);
				break;

			// new account
			case 2:
				System.out.println("Creating new bank account \nPlease enter initial account deposit: ");
				amt = scan.nextInt();
				scan.nextLine();
				dao.newBaccount(amt, currentUser.getId());
				break;

			// delete account
			case 3:
				System.out.println("Deleting bank account \nPlease enter id of account to delete: ");
				id = scan.nextInt();
				scan.nextLine();
				dao.deleteBaccount(id);
				break;

			// log out
			case 4:
				logout = true;
				break;

			// view all users
			case 5:
				List<AccountUser> accountUsers = new ArrayList<>();
				accountUsers = dao.readAllAccountUser();
				for (AccountUser u : accountUsers) {
					int uId = u.getId();
					dao.readAccountUser(uId);
				}
				break;

			// delete all users
			case 6:
				dao.deleteAllAcountUsers();
				break;

			// update user
			case 7:
				System.out.println("Please enter id of account user to update: ");
				id = scan.nextInt();
				scan.nextLine();
				System.out.println("Please enter username of account user to update: ");
				name = scan.nextLine();
				System.out.println("Please enter password of account user to update: ");
				pass = scan.nextLine();

				System.out.println("\n########################################");	
				System.out.println("You will now be logged in as the selected user without super permissions \n Select Logout to return to your SuperUser Account");
				System.out.println("########################################");	
				
				AccountUser newUser = new AccountUser(id, name, pass);
				userSwitch(scan, newUser, dao, exit);
				break;

			// create new user
			case 8:
				int supe = 0;

				System.out.println("Please enter username of new account user: ");
				name = scan.nextLine();
				System.out.println("Please enter password of new account user: ");
				pass = scan.nextLine();
				System.out.println("Is user a superUser? If so enter 1: ");
				supe = scan.nextInt();
				scan.nextLine();

				boolean supeR = false;
				if (supe == 1) {
					supeR = true;
				}
				AccountUser newUser1 = new AccountUser(name, pass, supeR);
				dao.createAccountUser(newUser1);
				break;

			// exit
			case 9:
				exit = true;
				break;
			}
		}
		return exit;
	}

	public static void main(String[] args) {
		BankDAO dao = new BankDAOImpl();
		AccountUser currentUser;

		boolean exit = false;
		int s = 0;
		@SuppressWarnings("unused")
		String username;
		@SuppressWarnings("unused")
		String pass;

		// make scanner object
		Scanner scan = new Scanner(System.in);

		while(exit == false) {
			// take in input from user
			while (s != 1 && s != 2 && s != 3 && s != 4) {
				
				System.out.println("________________________________________");
				System.out.println("Press 1 to Login \n" + "Press 2 to create an Account\n"
						+ "Press 3 to create a SuperUser Account\n" + "Press 4 to exit");
				System.out.println("________________________________________");
				
				s = scan.nextInt();
				scan.nextLine();
			}

			switch (s) {
			case 1:
				// login

				System.out.println("ENTER USERNAME: ");
				username = scan.nextLine();

				System.out.println("ENTER PASSWORD: ");
				pass = scan.nextLine();
				currentUser = new AccountUser(username,pass);
				dao.initAccountUser(currentUser);
				
				if(currentUser.isSuper() == true) {
					exit = superSwitch(scan, currentUser, dao, exit);
				}
				else {
					exit = userSwitch(scan, currentUser, dao, exit);
				}
				break;

			case 2:
				// create account

				System.out.println("CREATE USERNAME: ");
				username = scan.nextLine();

				System.out.println("CREATE PASSWORD: ");
				pass = scan.nextLine();
				currentUser = new AccountUser(username, pass, false);
				userSwitch(scan, currentUser, dao, exit);
				break;
			case 3:
				// create super account

				System.out.println("CREATE USERNAME: ");
				username = scan.nextLine();

				System.out.println("CREATE PASSWORD: ");
				pass = scan.nextLine();
				currentUser = new AccountUser(username, pass, true);
				exit = superSwitch(scan, currentUser, dao, exit);
				break;
			case 4:
				exit = true;
				break;
			}
		}
		System.out.println("Exiting Banking App");
		scan.close();
	}

}
