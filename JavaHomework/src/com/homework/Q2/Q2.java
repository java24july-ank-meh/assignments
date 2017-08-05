package com.homework.Q2;

public class Q2 {
	public static void main (String[] args) {
		int num1 = 0;
		int num2 = 1;
		int num3 = 1;
		//base case
		System.out.print("0" + " 1 ");
		
		//calculate n3 = n2 + n1 for the next 23 numbers
		for (int i = 0; i < 23; ++i) {
			num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
			System.out.print(num3 + " ");
		}
	}
}
