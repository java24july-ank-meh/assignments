package com.Revature.StringDemo;

public class StringDemo {

	public static void main(String[] args) {
		String str1 = "Hello World";
		String str2 = new String( "Helldo World");
		
		if (str1 == str2) {
			System.out.println("Equals");
		}
		else if (str1.equals(str2)){
			System.out.println(".Equals");
		}
		else{
			System.out.println("Not Equals");
		}

	}

}
