package com.homework.Q6;

public class Q6 {

	public static void main(String[] args) {
		
		//Examples
		even(11);
		even(-20);
		even(0);
	}
	
	public static void even(int number) {
		Integer i;
		
		//Used Integer method remainderUnsigned to act as the modulus operator
		if(Integer.remainderUnsigned(number, 2) == 0) {
			System.out.println("EVEN");
		} else {
			System.out.println("ODD");
		}
		
	}
}
