package com.doubletrouble.domain;

import java.awt.Image;
import java.util.Date;

public class Reimbursements {

	private int id;
	private double amount;
	private String description;
	private Image reciept;
	private String submitted;
	private String resolved;
	private int author;
	private int resolver;
	private int type;
	private int status;
	
	public Reimbursements() {
	}

	public Reimbursements(int id, double amount, String description, int author) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getReciept() {
		return reciept;
	}

	public void setReciept(Image reciept) {
		this.reciept = reciept;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
