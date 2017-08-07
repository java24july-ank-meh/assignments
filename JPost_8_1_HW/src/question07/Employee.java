package question07;

import java.util.Comparator;

public class Employee{
	private String name;
	private String department;
	private int age;
	
	public Employee(String n, String d, int a) {
		name = n;
		department = d;
		age = a;
	}
	
	public Employee() {}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
}

class sortEmployee implements Comparator<Employee>{
	public int compare(Employee o1, Employee o2) {
	if(o1.getName().compareTo(o2.getName()) != 0)
		return o1.getName().compareTo(o2.getName());
	else if(o1.getDepartment().compareTo(o2.getDepartment()) != 0)
		return o1.getDepartment().compareTo(o2.getDepartment());
	else
		return o1.getAge() - o2.getAge();
	}
}
