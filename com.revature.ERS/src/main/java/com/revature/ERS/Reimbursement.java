package com.revature.ERS;

import java.sql.Date;;

public class Reimbursement {
	double r_amount;
	int r_id, u_id_author, u_id_resolver, rt_type, rs_status;
	String r_description;
	Date r_submited, r_resolved;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Reimbursement(double r_amount, int u_id_author, int u_id_resolver, int rt_type, int rs_status,
			String r_description, Date r_submited, Date r_resolved) {
		super();
		this.r_amount = r_amount;
		this.u_id_author = u_id_author;
		this.u_id_resolver = u_id_resolver;
		this.rt_type = rt_type;
		this.rs_status = rs_status;
		this.r_description = r_description;
		this.r_submited = r_submited;
		this.r_resolved = r_resolved;
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

	public int getRt_type() {
		return rt_type;
	}

	public void setRt_type(int rt_type) {
		this.rt_type = rt_type;
	}

	public int getRs_status() {
		return rs_status;
	}

	public void setRs_status(int rt_status) {
		this.rs_status = rt_status;
	}

	public String getR_description() {
		return r_description;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	public Date getR_submited() {
		return r_submited;
	}

	public void setR_submited(Date r_submited) {
		this.r_submited = r_submited;
	}

	public Date getR_resolved() {
		return r_resolved;
	}

	public void setR_resolved(Date r_resolved) {
		this.r_resolved = r_resolved;
	}

}
