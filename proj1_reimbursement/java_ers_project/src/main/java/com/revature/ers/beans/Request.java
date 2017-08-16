package com.revature.ers.beans;

import java.sql.Blob;
import java.sql.Date;

public class Request {
	private int id;
	private double amount;
	private String description;
	private boolean has_receipt;
	private Date submitted_date;
	private Date resolved_date;
	private int author_id;
	private String author_name;
	private int resolver_id;
	private String resolver_name;
	private RequestType type;
	private RequestStatus status;
	public Request(int id, double amount, String description, boolean has_receipt, Date submitted_date,
			Date resolved_date, int author_id, String author_name, int resolver_id, String resolver_name,
			RequestType type, RequestStatus status) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.has_receipt = has_receipt;
		this.submitted_date = submitted_date;
		this.resolved_date = resolved_date;
		this.author_id = author_id;
		this.author_name = author_name;
		this.resolver_id = resolver_id;
		this.resolver_name = resolver_name;
		this.type = type;
		this.status = status;
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
	public boolean getReceipt_image() {
		return has_receipt;
	}
	public void setReceipt_image(boolean has_receipt) {
		this.has_receipt = has_receipt;
	}
	public Date getSubmitted_date() {
		return submitted_date;
	}
	public void setSubmitted_date(Date submitted_date) {
		this.submitted_date = submitted_date;
	}
	public Date getResolved_date() {
		return resolved_date;
	}
	public void setResolved_date(Date resolved_date) {
		this.resolved_date = resolved_date;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getResolver_id() {
		return resolver_id;
	}
	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}
	public String getResolver_name() {
		return resolver_name;
	}
	public void setResolver_name(String resolver_name) {
		this.resolver_name = resolver_name;
	}
	public RequestType getType() {
		return type;
	}
	public void setType(RequestType type) {
		this.type = type;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Request [id=" + id + ", amount=" + amount + ", description=" + description + ", has_receipt="
				+ has_receipt + ", submitted_date=" + submitted_date + ", resolved_date=" + resolved_date
				+ ", author_id=" + author_id + ", author_name=" + author_name + ", resolver_id=" + resolver_id
				+ ", resolver_name=" + resolver_name + ", type=" + type + ", status=" + status + "]";
	}
}
