package com.revature.Q7;

import java.util.Comparator;

public class Q7 {
	public class Employee {

		@Override
		public String toString() {
			return "Employee [age=" + age + ", name=" + name + ", department=" + department + "]";
		}

		public int age;
		public String name;
		public String department;
	}
	public static void employeeSort(Employee a,Employee b) {
		if (a.age >= b.age) {
			System.out.println(a.toString()+ ", " + b.toString());
			
		}
		else {
			System.out.println(b.toString()+ ", " + a.toString());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
