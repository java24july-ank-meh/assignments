package com.Revature.comparison;

public class Employee implements Comparable{
	private String name ="";
	private String department = "";
	private int age = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Employee(){
		
	}
	public Employee(String name, String department, int age){
		this.name = name;
		this.department = department;
		this.age = age;
	}
	@Override
	public String toString(){
		
		return "\n" + name + "\t" + department + "\t" + age;
		
	}

	@Override
	public int compareTo(Object obj2) {
		Employee E2 = (Employee)obj2;
		if(!this.getName().equals(E2.getName())){
			return this.getName().compareTo(E2.getName());
		}
		else if(!this.getDepartment().equals(E2.getDepartment())){
			return this.getDepartment().compareTo(E2.getDepartment());
		}
		else
			return Integer.toString(this.getAge()).compareTo(Integer.toString(E2.getAge()));
	}
}

