package com.revature.assignments.question7;

import java.util.Comparator;

public class ComparatorName implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		int result = (o1.getName().compareTo(o2.getName()));
		if(result > 0)
			return -1;
		else if(result < 0)
			return 1;
		else
			return 0;
		
	}
}
