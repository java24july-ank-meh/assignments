package com.revature.Q14;

import java.util.Date;

public class Switch {

	public static void main(String[] args) {
		int i=0;
		
		switch(i) {
		case 1:
			System.out.println(Math.sqrt(50));
			break;
		case 2:
			Date today = new Date();
			System.out.println(today); 
			break;
		case 3:
			String[] str = new String("I am learning Core Java").split(" ");
			System.out.println(str);
			break;
		}
	}

}
