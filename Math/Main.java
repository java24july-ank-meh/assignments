package com.revature.challenges.mathassignment;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MathHelper mh = new MathHelper();

		ArrayList<Integer> al = mh.fibonacciSequence1(0, 1, 25);

		int count = 1;

		for(Integer i: al) {
			System.out.println(count+": "+i);
			count++;
		}


		System.out.println("(1)Factorial of 3 should be 6: "+mh.nFactorial1(3));
		System.out.println("(1)Factorial of 4 should be 24: "+mh.nFactorial1(4));
		System.out.println("(1)Factorial of 5 should be 120: "+mh.nFactorial1(5));
		System.out.println("(1)Factorial of 7 should be 5040 "+mh.nFactorial1(7));
		System.out.println("(1)Factorial of 10 should be 3628800: "+mh.nFactorial1(10));

		System.out.println("(2)Factorial of 3 should be 6: "+mh.nFactorial2(3));
		System.out.println("(2)Factorial of 4 should be 24: "+mh.nFactorial2(4));
		System.out.println("(2)Factorial of 5 should be 120: "+mh.nFactorial2(5));
		System.out.println("(2)Factorial of 7 should be 5040 "+mh.nFactorial2(7));
		System.out.println("(2)Factorial of 10 should be 3628800: "+mh.nFactorial2(10));





	}

}
