package revature.takeHome7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args){
		Employee Peter = new Employee("Peter", "Photography", 22);
		Employee Karen = new Employee("Karen", "QA", 29);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(Peter);
		employees.add(Karen);
		
		Collections.sort(employees, new EmployeeAgeComparator());
		for (Employee e : employees){
			System.out.println(e);
		}
		
		Collections.sort(employees, new DepartmentComparator());
		for (Employee e : employees){
			System.out.println(e);
		}
		
		Collections.sort(employees, new NameComparator());
		for (Employee e : employees){
			System.out.println(e);
		}
		
	}	
}
