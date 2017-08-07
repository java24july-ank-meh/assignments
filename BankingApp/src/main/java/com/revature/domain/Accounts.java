package com.revature.domain;

public class Accounts {

	private int accountId;
	private float value;
	private String f_name;
	private int clientId;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public Accounts(int accountId, float value, String f_name, int clientId) {
		super();
		this.accountId = accountId;
		this.value = value;
		this.f_name = f_name;
		this.clientId = clientId;
	}
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Client_Account [Account Id=" + accountId + ", Value=" + value + ", Firstname=" + f_name + "Client Id= " + clientId + "]";
	}
}
