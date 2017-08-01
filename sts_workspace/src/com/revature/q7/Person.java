package com.revature.q7;

public class Person {

	private String name;
	private Department department;
	private int age;
	
	public Person(String name, Department department, int age) {
		this.name = name; this.department = department; this.age = age;
	}
	
	public Person() {
		
	}
	
	static enum Department{
		Sales, HR, Recruiting, QA, DevOps
	}
	
	public int departmentRating(Department d) {
		switch (d) {
		case Sales: return 0;
		case HR: return 1;
		case Recruiting: return 2;
		case QA: return 3;
		case DevOps: return 4;
		default: return -1;
		}
		
	}
	
	public int departmentRating() {
		return departmentRating(this.department);
	}
	
	public String getName() {return this.name;}
	
	public Department getDepartment() {return this.department;}
	
	public int getAge() {return this.age;}
}


