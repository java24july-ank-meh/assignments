package com.revature.codechallenge;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Switch {
	public static void main(String[] args) {
		
		int i = 3;
		String[] str;
		int num = 4;
		
		switch(i) {
		case 1:
			System.out.println("The square root of " +num + " equals " + Math.sqrt(num));
			break;
		case 2:
			System.out.println((LocalDateTime.now().toLocalDate()));
			break;
		case 3:
			str = new String("I am learning Core Java").split("\\s");
			System.out.println(Arrays.toString(str));
			break;
		}
	}
} 
 