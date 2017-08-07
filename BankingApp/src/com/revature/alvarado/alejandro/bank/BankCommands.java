package com.revature.alvarado.alejandro.bank;

public enum BankCommands {
	
	// Normal User Commands
	VIEWACCOUNTS, CREATEACCOUNT, DELETEACCOUNT, 
	DEPOSIT, WITHDRAW,LOGOUT,
	
	// Super User Commands
	VIEWUSER, CREATEUSER, UPDATEUSER, DELETEUSER,
	
	// All User Commands
	HELP
}
