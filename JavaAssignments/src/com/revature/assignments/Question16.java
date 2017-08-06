package com.revature.assignments;




public class Question16 {

	public static void main(String[] args) {
		String str = args[0];
		CheckLength(str);

	}
	
	public static void CheckLength(String str) {
		int l;
		l = str.length();
		System.out.println("The length of your string is: "+l);
		
	}

}
