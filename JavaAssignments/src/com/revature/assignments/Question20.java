package com.revature.assignments;

public class Question20 {

	private String fName;
	private String lName;
	private int age;
	private String state;
	
	public static void main(String[] args) {
		Question20 test = new Question20();
		test.setfName("Eleazar");
		test.setlName("Rosales");
		test.setAge(24);
		test.setState("Illinois");
		test.PrintInfo();
	}
	
	public void PrintInfo(){
		System.out.println("Name: " +fName+" "+lName);
		System.out.println("Age: "+age+" years");
		System.out.println("State: "+ state+" State");
	}
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static void test() {
		System.out.println("print hello");
	}
}
