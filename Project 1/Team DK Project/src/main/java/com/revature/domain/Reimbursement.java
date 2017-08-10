package com.revature.domain;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
	//http://javaonlineguide.net/2014/06/java-sql-timestamp-in-java-example-convert-java-util-date-to-java-sql-timestamp.html
	private int rID;
	private int amount;
	private String description;
	private Blob receipt;
	private Timestamp submitted;
	private Timestamp resolved;
	private int authorID;		//or User author
	private int resolverID;		//or User resolver
	private int type;			//or String type
	private int status;			//or String status	
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	//------------------------------------------------------
	
	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getResolverID() {
		return resolverID;
	}

	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
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
