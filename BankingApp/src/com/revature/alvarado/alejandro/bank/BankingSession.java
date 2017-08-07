package com.revature.alvarado.alejandro.bank;

public interface BankingSession {
	public Boolean executeCommand(BankCommands bc);
	public void printPrompt();
}
