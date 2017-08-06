package com.revature.assignments;

public class Question5 {

	public static void main(String[] args) {
		System.out.println(FindMe("Hello, my name is Eleazar Rosales.", 6));
	}
	
	public static String FindMe(String str, int idx){
		StringBuilder result = new StringBuilder();
		String[] ss = str.split("");
		for(int i = 0; i <= idx; i++){
			result.append(ss[i]);
		}
		return result.toString();
	}
}
