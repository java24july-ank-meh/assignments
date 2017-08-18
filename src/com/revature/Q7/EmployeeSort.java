package com.revature.Q7;

import java.util.*;

class EmployeeSort {
	public static void main(String[] args) {
		ArrayList<Employee> obj = new ArrayList<Employee>();
		obj.add(new Employee(21, "secretary", "madison"));
		obj.add(new Employee(22, "programmer", "quincy"));
		obj.add(new Employee(37, "manager", "katie"));
		obj.add(new Employee(55, "president", "oliver"));
		obj.add(new Employee(42, "security", "belay"));
		obj.add(new Employee(38, "assistance", "mullu"));
		obj.add(new Employee(38, "assistance", "christine"));
		Collections.sort(obj, new SortEmployees());
		
		System.out.println(obj);
	}
}

class Employee {
	int age;
	String department;
	String name;

	public Employee (int age, String department, String name) {
		this.age = age;
		this.department = department;
		this.name = name;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", department=" + department + ", age=" + age + "]'\n'";
	}
}

class SortEmployees implements Comparator<Employee> {
	@Override
	public int compare(Employee obj1, Employee obj2) {
		int comp = obj1.name.compareTo(obj2.name);
		if(comp == 0) {
			comp = obj1.department.compareTo(obj2.department);
		}
		if (comp == 0) {
			comp = obj1.age - obj2.age;
		}
		return comp;
	}
}
