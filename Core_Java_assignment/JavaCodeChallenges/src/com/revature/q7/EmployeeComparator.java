package com.revature.q7;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee>{
	@Override
	public int compare(Employee arg0, Employee arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
}