package com.revature.NFactorial;

public class NFactorial {
	int nFactorial(int num) {
		int fact = 1;
		for(int i=1; i<=num; i++) {
			fact*=i;
		}
		return fact;
	}
	public static void main(String[] args) {
		NFactorial obj = new NFactorial();
		System.out.println(obj.nFactorial(5));
	}
}
