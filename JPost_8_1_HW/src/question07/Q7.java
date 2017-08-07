package question07;

import java.util.ArrayList;
import java.util.Collections;

public class Q7 {
	/*
	 * Question 7: Sort two employees based on their name,
	 * department, and age using the Comparator interface
	 */
	public static void main(String[] args) {
		//Q7 Test
		ArrayList<Employee> arr = new ArrayList<>();
		arr.add(new Employee("Josh", "Software", 23));
		arr.add(new Employee("Dave", "Accounting", 22));
		arr.add(new Employee("Dave", "Accounting", 21));
		arr.add(new Employee("Josh", "Human Relations", 23));
		System.out.println("Test\n\nUnsorted:");
		for(int x = 0; x < arr.size(); x++) {
			System.out.println(arr.get(x).getName() + ", " + arr.get(x).getDepartment() + ", " +
					arr.get(x).getAge());
		}
		Collections.sort(arr, new sortEmployee());
		System.out.println("\nSorted:");
		for(int x = 0; x < arr.size(); x++) {
			System.out.println(arr.get(x).getName() + ", " + arr.get(x).getDepartment() + ", " +
					arr.get(x).getAge());
		}
	}
}
