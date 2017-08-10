package com.revature.domain;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	//private blob receipt;
	private String submitted;
	private String resolved;
	private User author;
	private User resolver;
	private String type;
	private String status;
}
