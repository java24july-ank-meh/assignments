package com.ers.main;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ReimbursementRequest {
	
	// Fields
	private int rid;
	private double amount;
	private String description;
	private BufferedImage receiptImg;
	private Date dateSubmitted;
	private Date dateResolved;
	private int authorId;
	private int resolverId;
	private int type; // 1=travel, 2=materials, 3=licensing/certification, 4=other
	private int status; // 1=pending, 2=approved, 3=denied
	
	// Constructors
	public ReimbursementRequest() {
		super();
	}
	public ReimbursementRequest(int rid, double amount, String description, BufferedImage receiptImg,
			Date dateSubmitted, Date dateResolved, int authorId, int resolverId, int type, int status) {
		super();
		this.rid = rid;
		this.amount = amount;
		this.description = description;
		this.receiptImg = receiptImg;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}
	
	// Getters and Setters
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
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
	public BufferedImage getReceiptImg() {
		return receiptImg;
	}
	public void setReceiptImg(BufferedImage receiptImg) {
		this.receiptImg = receiptImg;
	}
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public Date getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(Date dateResolved) {
		this.dateResolved = dateResolved;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
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
	
	
	// String conversion method
	@Override
	public String toString() {
		return "ReimbursementRequest [rid=" + rid + ", amount=" + amount + ", description=" + description
				+ ", receiptImg=" + receiptImg + ", dateSubmitted=" + dateSubmitted + ", dateResolved=" + dateResolved
				+ ", authorId=" + authorId + ", resolverId=" + resolverId + ", type=" + type + ", status=" + status
				+ "]";
	}
	
	
	
}
