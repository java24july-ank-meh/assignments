package Q11;

import Q5.Question5;

public class Quesiton11 {
	
	/*
	 * Q11 Write a program that would access two float-variables from a class that exists
	 * in another package.
	 */
	public static void accessOutsideFloats() {
		System.out.println(Question5.yi);
		System.out.println(Question5.er);
	}

	public static void main(String[] args) {
		accessOutsideFloats();

	}

}
