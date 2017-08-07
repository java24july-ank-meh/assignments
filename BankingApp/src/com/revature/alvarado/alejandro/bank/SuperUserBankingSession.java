package com.revature.alvarado.alejandro.bank;

import java.util.InputMismatchException;
import java.util.List;

import com.revature.alvarado.alejandro.bank.db.BankUserDAOImpl;
import com.revature.alvarado.alejandro.bank.db.UserNotFoundException;

public class SuperUserBankingSession extends UserBankingSession {
	
	private String prompt = "super> ";
	private BankUserDAOImpl userDAO;
	
	public SuperUserBankingSession(BankUser user) {
		super(user);
		userDAO = new BankUserDAOImpl();
	}

	@Override
	public Boolean executeCommand(BankCommands command) {
				
		switch(command) {
		
		case VIEWUSER:
			this.viewUsers();
			break;
		case CREATEUSER:
			this.createUsers();
			break;
		case UPDATEUSER:
			this.updateUsers();
			break;
		case DELETEUSER:
			this.deleteUsers();
			break;
		case LOGOUT:
			return this.logout();
		case HELP:
			this.printHelpInstructions();
			break;
		default:
			System.out.println("Unkown command");
		}
		
		return false;
	}

	private Integer initialPrompt(String command) {
		System.out.println("Which user id do you want to " + command + "? (0..*)");
		System.out.print(prompt);
		
		Integer userId = null;
		try {
			userId = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Input was not a integer");
		}
		return userId;
	}
	
	private void endPrompt(String command, Boolean success) {
		if (success) {
			System.out.println(command + " was successful");
		} else {
			System.out.println(command + " was not successful");
		}
	}

	private void deleteUsers() {
		Integer userId = this.initialPrompt("delete");
		Boolean success = false;
		success = userDAO.deleteBankUser(userId);
		this.endPrompt("Delete", success);
	}

	private void updateUsers() {
		Integer userId = this.initialPrompt("update");
		
		try {
			user = userDAO.getBankUser(userId);
			
			System.out.println("Updating User: " + userId);
			System.out.println("Set username:");
			System.out.print(prompt);
			String username = scanner.next();
			
			System.out.println("Set password:");
			System.out.print(prompt);
			String password = scanner.next();
			
			System.out.println("Set admin:");
			System.out.print(prompt);
			Boolean isAdmin = scanner.nextBoolean();
			
			BankUser user = new BankUser(userId, username, isAdmin, password);
			
			userDAO.updateBankUser(user);
			
		} catch (UserNotFoundException e) {
			System.out.println("User not found");
			this.endPrompt("Update", false);
			return;
		} catch (InputMismatchException e) {
			System.out.println("Input was inccorrect");
			this.endPrompt("Update", false);
			return;
		}
		
		this.endPrompt("Update", true);
	}

	private void createUsers() {
		
		try {
			System.out.println("Creating User:");
			System.out.println("Set username:");
			System.out.print(prompt);
			String username = scanner.next();
			
			System.out.println("Set password:");
			System.out.print(prompt);
			String password = scanner.next();
			
			System.out.println("Set admin:");
			System.out.print(prompt);
			Boolean isAdmin = scanner.nextBoolean();
			
			BankUser user = new BankUser(null, username, isAdmin, password);
			
			userDAO.createBankUser(user);
			
		}  catch (InputMismatchException e) {
			System.out.println("Input was inccorrect");
			this.endPrompt("Create", false);
			return;
		}
		
		this.endPrompt("Create", true);
		
	}

	private void viewUsers() {
		
		System.out.println("Viewing all users:");
		
		List<BankUser> users = userDAO.getAllBankUsers();
		
		for (BankUser user: users) {
			System.out.println();
			System.out.println("User Id: " + user.getUserId());
			System.out.println("User name: " + user.getName());
			System.out.println("Admin? : " + user.getIsAdmin());
		}
		
	}

	private void printHelpInstructions() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Super User Commands List");
		System.out.println("View users  (VIEWUSER)");
		System.out.println("Create user (CREATEUSER)");
		System.out.println("Update user (UPDATEUSER)");
		System.out.println("Delete user (DELETEUSER)");
		System.out.println("Logout      (LOGOUT)");
		System.out.println("Help        (HELP)");
	}

	@Override
	public void printPrompt() {
		System.out.print(prompt);
	}
}
