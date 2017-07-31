package com.revature.challenge;

public class Main {

	public static void main(String[] args) {
		
		StringStuff tester1 = new StringStuff();
		MathStuff tester2 = new MathStuff();
		
		String str = "dlrow olleh";
		
		System.out.println(tester1.reverseString(str));
		System.out.println(tester1.subString(str, 5));
		
		tester2.fibonacci(25);
		tester2.factorial(5);
		
		// just print the methods

	}

}
