package com.revature.q2;

import java.util.ArrayList;

public class Fibonacci {
	public static ArrayList<Integer> fib(int x) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		for(int i = 1; i < x; i++) {
			numbers.add(numbers.get(i) + numbers.get(i-1));
		}
		numbers.remove(0);
		return numbers;
	}
}
