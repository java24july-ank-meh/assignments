package com.revature.q7;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

	@Override
	public int compare(Person p0, Person p1) {
		//returns 1 if p0 > p1, 0 if equal, -1 if p0 < p1
		
		int nameComparison = p0.getName().compareTo(p1.getName());
		if(nameComparison != 0) {return nameComparison;}
		
		int deptRating0 = p0.departmentRating();
		int deptRating1 = p1.departmentRating();
		int deptComparison = deptRating0 - deptRating1;
		
		if(deptComparison != 0) {return deptComparison;}
		
		int ageComparison = p0.getAge()-p1.getAge();
		return ageComparison;
	}

}
