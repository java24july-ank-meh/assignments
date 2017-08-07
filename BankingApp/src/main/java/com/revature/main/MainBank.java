package com.revature.main;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.*;
import com.revature.domain.*;

public class MainBank {
	
	Clients c = new Clients();
	Accounts account = new Accounts();
	static Scanner GUI = new Scanner(System.in);
	
	public static void Admin() {
		SuperUserFace admin = new S_User();
		while(true) {
			System.out.println("Hello Admin. What would you like to do today?");
			System.out.println("1: Add Client");
			System.out.println("2: View a Customer profile");
			System.out.println("3: View all Customer profiles");
			System.out.println("4: Update a Customer's profile");
			System.out.println("5: Delete a Customer's profile");
			System.out.println("6: Delete all Customer profiles");
			System.out.println("7: Log off from Admin");
			int superChoice = GUI.nextInt();
			switch(superChoice) {
				case 1:	System.out.println("Adding a new customer. Please wait one moment.");
					admin.addClient();
					continue;
					
				case 2:	System.out.println("Please input the id of the customer you wish to view");
						int id = GUI.nextInt();
						admin.viewClient(id);
						continue;
					
				case 3: System.out.println("Gathering the profiles of all customers. Please wait one moment");
						List<Clients> customers = admin.viewAllClients();
						int si = customers.size();
						if(si == 0) {
							System.out.println("There are no customers in our Database");
							continue;
						}
						Iterator itr = customers.iterator();
						System.out.println("Here are the clients in the database");
						
						while(itr.hasNext()) {
							Object client = itr.next();
							System.out.println(client);
						}
						continue;
						
				case 4: System.out.println("Type in the id of the account being updated:");
						int upda = GUI.nextInt();
						admin.updateClient(upda);
						continue;
					
				case 5: System.out.println("Please input the id of the customer profile that is being deleted:");
						int d_id = GUI.nextInt();
						admin.deleteClient(d_id);
						continue;
				case 6: System.out.println("Are you sure you want to remove all customers from the database? Y/N");
						String check = GUI.next();
						if(check.equals("Y")) {
							System.out.println("Removing all customers from Database");
							admin.deleteAllClients();
							continue;
						} else {
							continue;
						}
				
				case 7: System.out.println("Logging off. Have a good day Admin");
					break;
					
				default: System.out.println("Something went horribly wrong!");
					break;	
			}
			break;
		}
	}
	
	
	public static void Customer() {
		SuperUserFace admin = new S_User();
		NormalUserFace customer = new N_User();
		System.out.println("Are you a registered customer? Y/N");
		String check = GUI.next();
		if(check.equals("N")) {
			System.out.println("Ok. Let's get you registered then.");
			admin.addClient();
		}
		while(true) {
			System.out.println("Hello valued customer. Thank you for using our Simple Banking App.");
			System.out.println("What would you like to do today?");
			System.out.println("1: Add an Account");
			System.out.println("2: View an Account");
			System.out.println("3: View all your Accounts");
			System.out.println("4: Make a deposit");
			System.out.println("5: Make a withdrawl");
			System.out.println("6: Delete an Account");
			System.out.println("7: Delete multiple Accounts");
			System.out.println("8: Logout of your Accounts");
			int userChoice = GUI.nextInt();
			switch(userChoice) {
				case 1:System.out.println("Creating a new Account");
						customer.createAccount();
					continue;
				case 2: System.out.println("Please enter the Account Id that you wish to view.");
						int a_id = GUI.nextInt();
						System.out.println("Accessing the Account Database. Wait one moment please.");
						customer.viewAccount(a_id);
					continue;
				case 3:System.out.println("Please enter you customer Id to view all your Accounts.");
						int u_ID = GUI.nextInt();
						System.out.println("Accessing the Account Database. Wait one moment please.");
						customer.viewAllAccounts(u_ID);
					continue;
				case 4: System.out.println("Please enter the account ID, your Firstname, and the ammount your wish to deposit");
						System.out.println("Account ID:");
						int aId = GUI.nextInt();
						System.out.println("Firstname: ");
						String f_Name = GUI.next();
						System.out.println("Ammount: ");
						float amt = GUI.nextFloat();
						customer.depositMoney(aId, f_Name, amt);
					continue;
				case 5:System.out.println("Please enter the account ID, your Firstname, and the ammount your wish to withdraw");
					   System.out.println("Account ID:");
					   int aID = GUI.nextInt();
					   System.out.println("Firstname: ");
					   String f_name = GUI.next();
					   System.out.println("Ammount: ");
					   float ammt = GUI.nextFloat();
					   customer.withdrawtMoney(aID, f_name, ammt);
					continue;
				case 6:System.out.println("Please enter the Account ID of the account you wish to delete.");
					int d_id = GUI.nextInt();
					customer.deleteAccount(d_id);
					continue;
				case 7:System.out.println("Please enter your customer ID");
						int ud_ID = GUI.nextInt();
						customer.deleteAllAcounts(ud_ID);
					continue;
				case 8:System.out.println("Logging out now. Have a nice day.");
					break;
				default:System.out.println("UH OH! Something went horribly wrong.");
					break;
			}
			
			break;
		}
	}
	public static void main(String[] args) {
		
		//admin.addClient();
		//customer.createAccount();
		while(true) {
			System.out.println("Hello. Welcome to the Simple Banking App");
			System.out.println("If you are the Admin, please enter 1.");
			System.out.println("If you are a customer please enter 2.");
			System.out.println("If you are done using this app enter 3.");
			int userType = GUI.nextInt();
			switch(userType) {
				case 1:
					Admin();
					continue;
				case 2: 
					Customer();
					continue;
				case 3: System.out.println("Turning off the app. Have a good day."); 
					break;
				default: continue;
			}
			
			break;
		}

	}
}
