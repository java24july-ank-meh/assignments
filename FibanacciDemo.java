package com.revature.fibanacci;

import java.util.Scanner;

public class FibanacciDemo{
	
	public static void main(String[] args) {
		
		int number;
		int a = 0;
		int b = 1;
		int total;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("enter a number");
		number = keyboard.nextInt();
		
		System.out.print(a+ " "+ b+ " ");
		
		for(int i = 0; i < number; i++) {
			
			total = a+b;
			a=b;
			b = total;
			
			System.out.print(b + " ");
		}
		
		
	}
	
	
	
	
	
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    