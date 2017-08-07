package com.revature.Customers;

public class Customers extends Accounts {
	private String firstName;
	private String lastName;
	private String username;
	private char password;
	private int age;
	private String phoneNumber;
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getPassword() {
		return password;
	}
	public void setPassword(char password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Customers() {
		super();
	}
	public Customers(String firstName, String lastName, String username, char password, int age, String phoneNumber,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customers [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", age=" + age + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	
}
