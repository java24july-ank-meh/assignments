package Q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Question7 {
	/*
	 * Q7 Sort two employees based on their name, department, and age using Comparator 
	 * interface.
	 */
	public static void mySort(ArrayList<Employee> arr) {
		Collections.sort(arr, new EmployeeComp());
	}
	
	public static void main(String args[]) {
		ArrayList<Employee> e = new ArrayList<Employee>();
		e.add(new Employee("Rob","Fragrance",30));
		e.add(new Employee("Bob","Fragrance",25));
		e.add(new Employee("Bob","Electronics",19));
		e.add(new Employee("Zyra", "Fragrance",29));
		System.out.println(e.toString());
		System.out.println();
		mySort(e);
		System.out.println(e.toString());
	}
}



/*
 * EmployComp that implements Comparator to do Q7
 */
class EmployeeComp implements Comparator<Employee>{
	public int compare(Employee e1, Employee e2) {
		if(e1.getName().compareTo(e2.getName())>0)
			return 1;
		else if(e1.getName().compareTo(e2.getName())<0)
			return -1;
		else {
			if(e1.getDepartment().compareTo(e2.getDepartment())>0)
				return 1;
			else if(e1.getDepartment().compareTo(e2.getDepartment())<0)
				return -1;
			else {
				if(e1.getAge()-e2.getAge()>0)
					return 1;
				else if(e1.getAge()-e2.getAge()<0)
					return -1;
			}
		}
		return 0;
	}
}
/*
 * Employee class to do Q7
 */
class Employee{
	private String name;
	private String department;
	private int age;
	
	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}

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

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

