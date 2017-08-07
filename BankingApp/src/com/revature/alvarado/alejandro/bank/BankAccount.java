package com.revature.alvarado.alejandro.bank;

public class BankAccount {

	private Integer accountId;
	private Double amount;
	
	public BankAccount(Integer accountId, Double amount) {
		this.accountId = accountId;
		this.amount = amount;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public Double getAmount() {
		return amount;
	}
	
}
