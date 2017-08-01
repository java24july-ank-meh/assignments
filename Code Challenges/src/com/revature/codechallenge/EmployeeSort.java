package com.revature.codechallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Employee {
	
	String name;
	String department;
	int age;

	public Employee (String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	
	
	
}

class EmployeeSorter implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		int result = o1.name.compareTo(o2.name);
		
		if(result == 0) {
			result = o1.department.compareTo(o2.department);
		}
		if (result == 0) {
			result = o1.age - o2.age;
		}
		
		return result;
	}
	
}

public class EmployeeSort {
	public static void main(String[] args) {
		ArrayList<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee("John", "Marketing", 20));
		emp.add(new Employee("John", "HR", 25));
		emp.add(new Employee("John", "HR", 22));
		emp.add(new Employee("Amy", "HR", 32));
		Collections.sort(emp, new EmployeeSorter());
		
		System.out.println(emp);
	}

}


