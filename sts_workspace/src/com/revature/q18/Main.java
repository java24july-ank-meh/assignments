package com.revature.q18;

public class Main {

	public static void main(String[] args) {
		Parent obj = new Child();
		System.out.println(obj.containsUppercase("lkhg"));
		System.out.println(obj.containsUppercase("asdfRas"));
		
		System.out.println(obj.toUpper("YYYyYYyy"));
		System.out.println(obj.toUpper("sdfdfndf"));
		
		System.out.println(obj.toIntPlus10("65"));
		System.out.println(obj.toIntPlus10("89"));
	}
}
