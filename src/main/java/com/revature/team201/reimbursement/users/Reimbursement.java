package com.revature.team201.reimbursement.users;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reimbursement {
	// Instance variables
	private Integer rId;
	private Double amount;
	private String description;
	private BufferedImage receipt;
	private Timestamp dateSubmitted;
	private Timestamp dateResolved;
	private Integer author;
	private Integer resolver;
	private String type;
	private Status status;
	private boolean hasReceipt = false;
	
	public Reimbursement() {}
	public Reimbursement(Double amt, String desc, BufferedImage receipt, Integer auth, String t) {
		this.amount = amt;
		this.description = desc;
		this.receipt = receipt;
		this.dateSubmitted = new Timestamp(System.currentTimeMillis());
		this.author = auth;
		this.type = t;
		this.status = Status.PENDING;
		if (receipt == null)
			this.hasReceipt = false;
		else
			this.hasReceipt = true;
	}
	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", amount=" + amount + ", description=" + description + ", dateSubmitted="
				+ dateSubmitted + ", dateResolved=" + dateResolved + ", author=" + author + ", resolver=" + resolver
				+ ", type=" + type + ", status=" + status + ", hasReceipt=" + hasReceipt + "]";
	}
	public Reimbursement(Integer id, Double amt, String desc, BufferedImage receipt,
			 Timestamp submitted, Timestamp resolved, Integer auth, Integer resolve, 
			 String t, Status stat, boolean hasRec) {
		this.rId = id;
		this.amount = amt;
		this.description = desc;
		this.receipt = receipt;
		this.dateSubmitted = submitted;
		this.dateResolved = resolved;
		this.author = auth;
		this.resolver = resolve;
		this.type = t;
		this.status = stat;
		this.hasReceipt = hasRec;
	}
	
	// Getters and Setters
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public Timestamp getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(Timestamp dateResolved) {
		this.dateResolved = dateResolved;
	}
	public Integer getAuthor() {
		return author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public Integer getResolver() {
		return resolver;
	}
	public void setResolver(Integer resolver) {
		this.resolver = resolver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isHasReceipt() {
		return hasReceipt;
	}
	public void setHasReceipt(boolean hasReceipt) {
		this.hasReceipt = hasReceipt;
	}
	public BufferedImage getReceipt() {
		return receipt;
	}
	public void setReceipt(BufferedImage receipt) {
		this.receipt = receipt;
	}
}
