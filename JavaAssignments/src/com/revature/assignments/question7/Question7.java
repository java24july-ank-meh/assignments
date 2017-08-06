package com.revature.assignments.question7;

public class Question7 {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		
		emp1.setAge(24);
		emp2.setAge(30);
		emp1.setDepartment("Accounting");
		emp2.setDepartment("Sales");
		emp1.setName("AaEli");
		emp2.setName("Alma");
		
		ComparatorAge test = new ComparatorAge();
		ComparatorDepartment test2 = new ComparatorDepartment();
		ComparatorName test3 = new ComparatorName();
		
		System.out.println(test.compare(emp1, emp2));
		System.out.println(test2.compare(emp1, emp2));
		System.out.println(test3.compare(emp1, emp2));
	}
	
}
