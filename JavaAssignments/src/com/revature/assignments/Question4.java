package com.revature.assignments;

public class Question4 {

	public static void main(String[] args) {
		int i = 10;
		System.out.println(Facto(i));
	}
	
	public static int Facto(int num) {
		if (num == 1) {
			System.out.print("Total is: ");
			return 1;
		}
		return num * Facto(num - 1);
	}
}
