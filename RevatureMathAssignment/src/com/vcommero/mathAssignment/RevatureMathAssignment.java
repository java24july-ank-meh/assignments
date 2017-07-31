package com.vcommero.mathAssignment;

public class RevatureMathAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i=0; i<25; i++) {
			System.out.println("n="+i+": "+fibbonaci(i));
		}
		
		for (int i=1; i<10; i++) {
			System.out.println("n="+i+": "+factorial(i));
		}
	}
	
	public static int fibbonaci(int n) {
		int fn = 1;
		int fn_1 = 1;
		int fn_2 = 0;
		
		for (int i=0; i<n; i++) {
			fn = fn_1 + fn_2;
			fn_2 = fn_1;
			fn_1 = fn;
		}
		
		return fn;
	}
	
	public static int factorial(int n) {
		int f = n;
		for (int i=n-1; i>0; i--) {
			f *= i;
		}
		return f;
	}

}
