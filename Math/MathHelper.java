package com.revature.challenges.mathassignment;

import java.util.ArrayList;

public class MathHelper {

	public MathHelper() {

	}

	public ArrayList<Integer> fibonacciSequence1(int num1, int num2, int length) {

		ArrayList<Integer> seq = new ArrayList<Integer>();

		seq.add(num1);
		seq.add(num2);

		for (int i = 2; i < length; i++) {
			int temp1 = seq.get(i - 2);
			int temp2 = seq.get(i - 1);

			seq.add(temp1 + temp2);
		}

		return seq;
	}

	// ------------------------------------------------------------------------------------------------

	public int nFactorial1(int num) {
		//System.out.println("Factorial 1 is starting for, " + num);
		int number = 1;

		do {
			number *= num;
			//System.out.println("number " + number);
			num--;
			//System.out.println("num " + num);
		} while(num > 1);
		//System.out.println("Factorial 1 is ending for, " + num);
		return number;

	}

	public int nFactorial2(int num) {

		int number = num;

		do {
			//System.out.println("number " + number);
			number--;
			num *= (number);
			//System.out.println("Num: " + num);
			//System.out.println("number " + number);
		} while (number > 1);

		return num;

	}

}
