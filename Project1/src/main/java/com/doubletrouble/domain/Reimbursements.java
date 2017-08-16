package com.doubletrouble.domain;

public class Reimbursements {

	private int id;
	private double amount;
	private String description;
	public Reimbursements(double amount, String description, String submitted, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.type = type;
	}

	private String reciept;
	private String submitted;
	private String resolved;
	private int author;
	private int resolver;
	private String resolverName;
	private int type;
	private int status;
	
	public Reimbursements() {
	}

	public Reimbursements(int id, double amount, String description, String submitted, int author) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.author = author;
	}

	
	@Override
	public String toString() {
		return "Reimbursements [id=" + id + ", amount=" + amount + ", description=" + description + ", reciept="
				+ reciept + ", submitted=" + submitted + ", resolved=" + resolved + ", author=" + author + ", resolver="
				+ resolver + ", resolverName=" + resolverName + ", type=" + type + ", status=" + status + "]";
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

	public String getReciept() {
		return reciept;
	}

	public String getResolverName() {
		return resolverName;
	}

	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
	}

	public void setReciept(String reciept) {
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

	public Reimbursements(int id, double amount, String description, String submitted, String resolved, int type,
			int status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.resolved = resolved;
		this.type = type;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
