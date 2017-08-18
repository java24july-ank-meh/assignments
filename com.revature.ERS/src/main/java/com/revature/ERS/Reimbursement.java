package com.revature.ERS;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
	double r_amount;
	int r_id, foreign_id, u_id_author, u_id_resolver;
	String r_description, rt_type, rs_status;
	Timestamp r_submited, r_resolved;
	Blob receipt;
	byte[] blobBytes;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(double r_amount, int u_id_author, int u_id_resolver, String rt_type, String rs_status,
			String r_description) {
		super();
		this.r_amount = r_amount;
		this.u_id_author = u_id_author;
		this.u_id_resolver = u_id_resolver;
		this.rt_type = rt_type;
		this.rs_status = rs_status;
		this.r_description = r_description;
		this.r_submited = null;
		this.r_resolved = null;
		this.foreign_id = 0;
		this.receipt = null;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public double getR_amount() {
		return r_amount;
	}

	public void setR_amount(double r_amount) {
		this.r_amount = r_amount;
	}

	public int getU_id_author() {
		return u_id_author;
	}

	public void setU_id_author(int u_id_author) {
		this.u_id_author = u_id_author;
	}

	public int getU_id_resolver() {
		return u_id_resolver;
	}

	public void setU_id_resolver(int u_id_resolver) {
		this.u_id_resolver = u_id_resolver;
	}

	public String getRt_type() {
		return rt_type;
	}

	public void setRt_type(String rt_type) {
		this.rt_type = rt_type;
	}

	public String getRs_status() {
		return rs_status;
	}

	public void setRs_status(String rt_status) {
		this.rs_status = rt_status;
	}

	public String getR_description() {
		return r_description;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	public Timestamp getR_submited() {
		return r_submited;
	}

	public void setR_submited(Timestamp r_submited) {
		this.r_submited = r_submited;
	}

	public int getF_id() {
		return this.foreign_id;
	}

	public void setF_id(int foreign_id) {
		this.foreign_id = foreign_id;
	}

	public Timestamp getR_resolved() {
		return r_resolved;
	}

	public void setR_resolved(Timestamp r_resolved) {
		this.r_resolved = r_resolved;
	}

	public Blob get_blob() {
		return this.receipt;
	}

	public void set_blob(Blob receipt) {
		this.receipt = receipt;
	}

	public byte[] get_blobBytes() {
		return this.blobBytes;
	}

	public void set_blobBytes(byte[] blobBytes) {
		this.blobBytes = blobBytes;
	}

}
