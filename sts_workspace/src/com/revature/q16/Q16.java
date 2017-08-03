package com.revature.q16;

public class Q16 {

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("proper usage: java Q16 <string argument>"); System.exit(0);
		}
		
		System.out.println(args[0].length());

	}

}
