package com.homework.Q10;

public class Q10 {

	public static void main(String[] args) {
		// example
		System.out.println(min(4,3));
		System.out.println(min(4,4));
		System.out.println(min(3,4));
	}

	// method to find minimun value
	public static double min(double num1, double num2) {
		return num1 < num2 ? num1 : num2;
	}
}
