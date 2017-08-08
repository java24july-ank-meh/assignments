package com.revature.main;

import java.util.Arrays;

import com.revature.q1.BubbleSort;
import com.revature.q2.Fibonacci;
import com.revature.q3.ReverseString;
import com.revature.q4.Factorial;
import com.revature.q5.Substring;
import com.revature.q6.IsEven;
import com.revature.q7.Employee;
import com.revature.q7.EmployeeComparator;

public class Main {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(BubbleSort.sort(new int[] {1,0,5,6,3,2,3,7,9,8,4})));
		System.out.println(Fibonacci.fib(25));
		System.out.println(ReverseString.reverseString("Hello World"));
		System.out.println(Factorial.fact(25));
		System.out.println(Substring.substring("Hello World",7));
		System.out.println(IsEven.isEven(26));
		
		Employee[] employees = new Employee[] {new Employee("Jon","IT",22), new Employee("Chris","HR",20)};
		Arrays.sort(employees, new EmployeeComparator());
		System.out.println(Arrays.toString(employees));
	}
}
