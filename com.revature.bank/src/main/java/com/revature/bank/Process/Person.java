package com.revature.bank.Process;

public class Person {

	private String user_fname, user_lname, user_email, user_city, user_state, user_address;
	private int user_phone;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String user_fname, String user_lname, String user_email, String user_city, String user_state,
			int user_phone) {
		super();
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_email = user_email;
		this.user_city = user_city;
		this.user_state = user_state;
		this.user_phone = user_phone;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public int getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(int user_phone) {
		this.user_phone = user_phone;
	}

	@Override
	public String toString() {
		return "Person [user_fname=" + user_fname + ", user_lname=" + user_lname + ", user_email=" + user_email
				+ ", user_city=" + user_city + ", user_state=" + user_state + ", user_phone=" + user_phone + "]";
	}

}
