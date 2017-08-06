package com.revature.assignments;

public class Question6 {

	public static void main(String[] args) {
		System.out.println(ToBeEvenOrNot(58));
		System.out.println(ToBeEvenOrNot(54));
		System.out.println(ToBeEvenOrNot(98));
		System.out.println(ToBeEvenOrNot(1057));
		System.out.println(ToBeEvenOrNot(13));
		System.out.println(ToBeEvenOrNot(4));
		System.out.println(ToBeEvenOrNot(10937));
		System.out.println(ToBeEvenOrNot(0));
		System.out.println(5/2);
		
	}
	public static boolean ToBeEvenOrNot(int num) {
		int temp = num/2;
		if(temp*2 == num)
			return true;
		return false;
	}
}
