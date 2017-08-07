package com.roberto.employee;

import java.util.*;
public class SortEmployee {

	public static void main(String[] args) {
		
		
		
	}
	
	public void sortEmployeeMethod() {
		
		List<Employee> list = new ArrayList<Employee>();
		
		list.add(new Employee("Roberto Alvarez", "Software Development", 22));
		list.add(new Employee("Madrone Goldman", "Mobile App Development", 23));
		list.add(new Employee("Jules Jefferson", "Front-End Development", 20));
		
		Collections.sort(list);
		
		for(Employee emp : list) {
			System.out.println("Comparing by age");
			System.out.println(emp.getEmp_name());
		}
		
	}

}
