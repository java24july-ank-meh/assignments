package com.revature.models;

public class Account
{
	private int accountId;
	private String name;
	private double balance;

	public Account(String name, double balance)
	{

		this.name = name;
		this.balance = balance;
	}

	public Account(int accountId, String name, double balance)
	{
		this.accountId = accountId;
		this.name = name;
		this.balance = balance;
	}

	public int getAccountId()
	{
		return this.accountId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	public void deposit()
	{

	}

	public void withdraw(double amount)
	{

	}

	@Override
	public String toString()
	{
		return String.format("Account [name=" + name + ", balance= $%.2f]", this.balance);
	}

}
