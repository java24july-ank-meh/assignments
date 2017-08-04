package javaHomework.Q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Employee class
class Employee implements Comparable<Employee> {

	// constructor initializes all employee fields. For this example all fields are
	// immutable
	Employee(String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}

	private String name;
	private String department;
	private int age;

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public int getAge() {
		return age;
	}

	/*
	 * CustomComarator compares employees first by name then by department and then
	 * by age
	 */
	public static class SortByAll implements Comparator<Employee> {
		@Override
		public int compare(Employee e1, Employee e2) {
			int i = e1.getName().compareTo(e2.getName());
			if (i != 0)
				return i;

			i = e1.getDepartment().compareTo(e2.getDepartment());
			if (i != 0)
				return i;

			return Integer.compare(e1.getAge(), e2.getAge());
		}
	}

	// comparator to order by only name
	public static class SortByName implements Comparator<Employee> {
		@Override
		public int compare(Employee e1, Employee e2) {
			return e1.getName().compareTo(e2.getName());
		}
	}

	// comparator to order by only department
	public static class SortByDepartment implements Comparator<Employee> {
		@Override
		public int compare(Employee e1, Employee e2) {
			return e1.getDepartment().compareTo(e2.getDepartment());
		}
	}

	// comparator to order by only age
	public static class SortByAge implements Comparator<Employee> {
		@Override
		public int compare(Employee e1, Employee e2) {
			return Integer.compare(e1.getAge(), e2.getAge());
		}
	}

	/*
	 * Natural order sorting will be the same as sorting by age for Employee objects
	 * 
	 */
	@Override
	public int compareTo(Employee e) {
		return this.age > e.age ? 1 : (this.age < e.age ? -1 : 0);
	}

	// override toString method for easier printing
	@Override
	    public String toString(){
	        return ("[" + this.name + ", " + this.department + ", " +Integer.toString(this.age) + "]" );
	    }

}

public class EmployeeSort {

	public static void main(String[] args) {

		// create Employee objects to be sorted
		Employee e1 = new Employee("Gilflapple", "Sales", 182);
		Employee e2 = new Employee("Gilflapple", "HR", 3);
		Employee e3 = new Employee("Zafadoo", "HR", 28);
		Employee e4 = new Employee("Aardivarkius", "HR", 3);
		Employee e5 = new Employee("Scoish Maloish", "Sales", 26);
		Employee e6 = new Employee("Moofi", "LossPrevention", 1);
		Employee e7 = new Employee("Scoish Maloish", "LossPrevention", 26);

		// put Employee objects in sortable Collection
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
		employees.add(e6);
		employees.add(e7);

		// sort with SortByAll comparator
		Collections.sort(employees, new Employee.SortByAll());

		// print sorted list of employees
		for (Employee e : employees) {
			System.out.println(e);
		}
	}

}
