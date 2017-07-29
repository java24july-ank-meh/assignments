
package com.revature.main;
public class Main {
	public static void main(String[] args) {
		
		int f1= 0, f2= 1;
		
		int fib= 1;

		for (int i= 0; i<=24; i++)   {

			System.out.println(fib);
			fib= f1 + f2;
			f1= f2;
			f2= fib;
		}
	}
	
}