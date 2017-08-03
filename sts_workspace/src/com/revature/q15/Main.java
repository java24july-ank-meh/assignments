package com.revature.q15;

public class Main {

	public static void main(String[] args) {
		Mathable m = new MathableImpl();
		System.out.println("2.3 + 5.4 = "+m.addition(2.3, 5.4));
		System.out.println("2.3 - 5.4 = "+m.subtraction(2.3, 5.4));
		System.out.println("2.3 * 5.4 = "+m.multiplication(2.3, 5.4));
		System.out.println("2.3/5.4 = " + m.division(2.3, 5.4));
	}
}
