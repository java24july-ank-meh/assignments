package com.homework.Q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Q7 {

	public static void main(String[] args) {
		
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee("BOB", "BIO", 35);
		Employee e2 = new Employee("Fred", "CS", 24);
		Employee e3 = new Employee("Liinda", "PHIL", 3346);
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		//department sort
		Collections.sort(employees, new sortByDepartment());
		for(Employee e : employees) {
			System.out.println(e);
		}
		System.out.println();
		
		//age sort
		Collections.sort(employees, new sortByAge());
		for(Employee e : employees) {
			System.out.println(e);
		}
		System.out.println();
		
		//name sort
		Collections.sort(employees, new sortByName());
		for(Employee e : employees) {
			System.out.println(e);
		}
		
	}
	

		
}