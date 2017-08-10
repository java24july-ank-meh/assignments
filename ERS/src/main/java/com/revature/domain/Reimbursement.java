package com.revature.domain;

import java.sql.Date;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	//private blob receipt;
	private Date submitted;
	private Date resolved;
	private User author;
	private User resolver;
	private String type;
	private String status;
	
	
	public Reimbursement(int id, double amount, String description, Date date, User author, String type,
			String status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.submitted = date;
		this.author = author;
		this.type = type;
		this.status = status;
	}
	
	
}
