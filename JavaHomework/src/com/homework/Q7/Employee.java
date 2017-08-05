package com.homework.Q7;

import java.util.Comparator;

//basic employee class
public class Employee {
	String name;
	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}

	String department;
	int age;

	Employee(String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}

}

//compare
class sortByDepartment implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.department.compareTo(e2.department);
	}
}
	class sortByAge implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return e1.age - e2.age;
		}
	}

	class sortByName implements Comparator<Employee>{
		public int compare(Employee e1, Employee e2) {
			return e1.name.compareTo(e2.name);
		}
	}