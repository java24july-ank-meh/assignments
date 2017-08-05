package com.homework.Q4;

public class Q4 {

	public static void main(String[] args) {
		
		//N is the given number
		int N = 4;
		int result = 1;
		//base case
		if(N == 0)
			System.out.println(result);
		//loop through multiplying result times each number through til the given number
		for(int i = 1; i <= N; ++i) {
			result = result * i;
		}
		System.out.println(result);

	}

}
