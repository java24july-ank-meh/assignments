package com.roberto.employee;

import java.util.Comparator;

public class Employee implements Comparator<Employee>, Comparable<Employee>{
	/*
	 * Both TreeSet and TreeMap store elements in sorted order. 
	 * However, it is the comparator that defines precisely 
	 * what sorted order means.
	 */

	private String emp_name, department;
	private int age;

	public Employee(String emp_name, String department, int age) {
		super();
		this.emp_name = emp_name;
		this.department = department;
		this.age = age;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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

	public int compare(Employee emp1, Employee emp2) {
		return emp1.age - emp2.age;
	}

	public int compareTo(Employee emp) {
		return (this.emp_name).compareTo(emp.emp_name);
	}

}
