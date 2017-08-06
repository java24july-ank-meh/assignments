package com.revature.assignments.question7;

import java.util.Comparator;

public class ComparatorDepartment implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		int result = (o1.getDepartment().compareTo(o2.getDepartment()));
		if(result > 0)
			return -1;
		else if(result < 0)
			return 1;
		else
			return 0;
	}
}
