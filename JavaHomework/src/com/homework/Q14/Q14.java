package com.homework.Q14;

import java.time.LocalDate;

public class Q14 {

	public static void main(String[] args) {
		//basic fields
		int i = 3;
		double number = 16;
		String[] strings = new String[5];
		
		switch (i) {
		case 1:
			System.out.println(Math.sqrt(number));
			break;
		case 2:
			System.out.println(LocalDate.now());
			break;
		case 3:
			String string = "I am learning Core Java";
			//split on whitespace
			strings = string.split(" ");
			//view result
			for(String s: strings) {
				System.out.println(s);
			}
		}
		
		
	}

}
