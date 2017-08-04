package com.revature.Q6;

public class Q6 {
	public static void isEven(int x) {
		boolean a = false;
		int z = x / 2;
		if (x == z * 2) {
			a = true;
		}
		System.out.println(a);
	}
	public static void main(String[] args) {
		isEven(33);
		isEven(20);
	}
}
