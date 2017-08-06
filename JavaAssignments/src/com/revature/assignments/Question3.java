package com.revature.assignments;

public class Question3 {
	public static void main(String[] args) {
		System.out.println(Rewind("Hello, my name is Eleazar Rosales. How are you doing?"));
		String phrase = "Hello, my name is Eleazar Rosales. How are you doing?";
		char[] str = phrase.toCharArray();
		Rewind2(str);
		for(int i = 0; i<str.length;i++){
			System.out.print(str[i]);	
		}
	}
	
	public static String Rewind(String str){
		StringBuilder result = new StringBuilder();
		for(int i = str.length()-1; i >= 0; i--){
			result.append(str.charAt(i));
		}
		return result.toString();
	}
	
	public static void Rewind2(char[] str){
		for(int i = 0; i<=(str.length/2);i++){
			if(i == (str.length/2))
				continue;
			char temp = str[i];
			str[i] = str[((str.length-1)-i)];
			str[((str.length-1)-i)] = temp;
		}
	}
	
}
