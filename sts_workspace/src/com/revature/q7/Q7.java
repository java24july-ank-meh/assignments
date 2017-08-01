package com.revature.q7;

import com.revature.q7.Person.Department;

public class Q7 {

	public static void main(String[] args) {
		Person p0 = new Person("adsf", Department.Sales, 37);
		Person p1 = new Person("adsf", Department.DevOps, 40);
		
		PersonComparator pc = new PersonComparator();
		System.out.println(pc.compare(p0, p1));

	}
	
	

}
