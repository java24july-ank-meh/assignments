// Written by Eleazar Rosales
package com.revature.Assignment2;

public class Assignment2 {

	public static void main(String[] args) {
		System.out.println(Fibo(25));
		Fibo2(25);
		System.out.println(facto(4));

	}

	public static int Fibo(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;
		return ((Fibo(num - 1) + Fibo(num - 2)));

	}
	
	public static void Fibo2(int num) {
		int p1 = 0;
		int p2 = 1;
		int cur = 0;
		for(int i=0; i <= num; i++ ) {
			cur = p1 + p2;
			if(i == 0) {
				System.out.println(i+ ") "+"0");
				continue;
			}
			if(i == 1) {
				System.out.println(i+ ") "+"1");
				continue;
			}
			System.out.println(i+ ") "+cur);
			p1 = p2;
			p2 = cur;
		}
	}

	public static int facto(int num) {
		if (num == 1) {
			System.out.print("Total is: ");
			return 1;
		}
		return num * facto(num - 1);
	}

}
