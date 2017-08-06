package com.revature.assignments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Question14 {

	public static void main(String[] args) {
	
		int cur = 3;
		CaseExample(cur);
	
	}
	
	public static void CaseExample(int cur) {
		switch(cur) {
		case 1: double i = Math.random()*50;
				double r = Math.sqrt(i);
				System.out.println("Square root of "+i+ " is "+ r);
				break;
		case 2: DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				break;
		case 3: String str = "I am learning Core Java";
				String[] result = str.split(" ");
				System.out.println(Arrays.toString(result));
		
		}
	}
}
