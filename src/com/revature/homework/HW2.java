package com.revature.homework;
import java.util.ArrayList;

public class HW2 {

	public static void main(String[] args) {
		System.out.println(fib(25));
		System.out.println(fact(25));
	}
	
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
	
	public static int fact(int n){
		if(n == 0) return 1;
		return n * fact(n-1);
	}

}
