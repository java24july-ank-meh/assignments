package com.homework.Q9;

import java.util.ArrayList;

public class Q9 {

	public static void main(String[] args) {
		//filling array
		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i = 1; i < 101; ++i) {
			numbers.add(i);
		}
		//base case
		System.out.print(numbers.get(0) + " " + numbers.get(1) + " ");
		//loop through each number to check if it is a prime number
		for(int i = 1; i < 100; ++i) {
			for(int j = 1; j < i;++j) {
				//if the number is divisible by another number besides 1 move to the next number
				if((numbers.get(i)%numbers.get(j)) == 0)
					break;
				if(j == (i-1))
					System.out.print(numbers.get(i) + " ");
			}
		}

	}

}
