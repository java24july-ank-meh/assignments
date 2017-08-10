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
	private int type;
	private int status;
	
	
	public Reimbursement(int id, double amount, String description, Date date, User author, int type,
			int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.submitted = date;
		this.author = author;
		this.type = type;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public double getAmount() {
		return amount;
	}


	public String getDescription() {
		return description;
	}


	public Date getSubmitted() {
		return submitted;
	}


	public Date getResolved() {
		return resolved;
	}


	public User getAuthor() {
		return author;
	}


	public User getResolver() {
		return resolver;
	}


	public int getType() {
		return type;
	}


	public int getStatus() {
		return status;
	}
	
	
}
