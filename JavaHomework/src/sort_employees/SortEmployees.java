package sort_employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEmployees {

	public static void main(String[] args) {
		
		ArrayList<Employee> employeeList = new ArrayList<>();
		
		employeeList.add(new Employee("HR", "Steve", 24));
		employeeList.add(new Employee("Accounting", "Emily", 22));
		employeeList.add(new Employee("Engineering", "Bob", 32));
		employeeList.add(new Employee("HR", "Darell", 24));
		employeeList.add(new Employee("Marketing", "Mike", 21));
		employeeList.add(new Employee("HR", "Jhobe", 34));
		employeeList.add(new Employee("Engineering", "Steven", 64));
		employeeList.add(new Employee("Accounting", "Katerina", 34));
		employeeList.add(new Employee("Marketing", "Stephanie", 24));
		employeeList.add(new Employee("HR", "David", 78));
		employeeList.add(new Employee("HR", "Peter", 90));
		
		Collections.sort(employeeList, new EmployeeComparator());
		
		System.out.println(employeeList);
	}

}

class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getName().compareTo(o2.getName()) < 0) {
			return -1;
		} else if (o1.getName().compareTo(o2.getName()) > 0) {
			return 1;
		} else if (o1.getAge() < o2.getAge()) {
			return -1;
		} else if (o1.getAge() > o2.getAge()) {
			return 1;
		} else if (o1.getDepartment().compareTo(o2.getDepartment()) < 0) {
			return -1;
		} else if (o1.getDepartment().compareTo(o2.getDepartment()) > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}


class Employee {
	private String department;
	private String name;
	private int age; 
	
	public Employee(String department, String name, int age) {
		this.department = department;
		this.name = name;
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		return "Employee [department=" + department + ", name=" + name + ", age=" + age + "]";
	}
	
	
}