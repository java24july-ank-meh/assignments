package com.revature.domain;

public class Clients {
	private int id;
	private String f_name;
	private String l_name;
	private String p_word;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getP_word() {
		return p_word;
	}
	public void setP_word(String p_word) {
		this.p_word = p_word;
	}
	public Clients(int id, String f_name, String l_name, String p_word) {
		super();
		this.id = id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.p_word = p_word;
	}
	
	public Clients() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", Firstname=" + f_name + ", Lastname=" + l_name + ", Password= " + p_word + "]";
	}
}
