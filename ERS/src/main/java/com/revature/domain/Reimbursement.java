package com.revature.domain;

import java.sql.Blob;
import java.sql.Date;

public class Reimbursement {
	private int id;
	private double amount;
	private String description;
	private Blob receipt;
	private Date submitted;
	private Date resolved;
	private int author;
	private int resolver;
	private int type;
	private int status;

	public Blob getReceipt() {
		return receipt;
	}

	public Reimbursement(int id, double amount, String description, Blob receipt, Date submitted, Date resolved,
			int author, int resolver, int type, int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
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

	public int getAuthor() {
		return author;
	}

	public int getResolver() {
		return resolver;
	}

	public int getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

}