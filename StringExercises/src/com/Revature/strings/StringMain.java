package com.Revature.strings;

public class StringMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String teststring = "TestString";
		int testsub = 2;
		StringOps test = new StringOps(teststring);
		System.out.println("Original: " + teststring);
		System.out.println("Reversed: " + test.reverse());
		System.out.println("Substring 3:" + test.substring(3));
		System.out.println("Substring Var:" + test.substring(testsub));
	}

}
