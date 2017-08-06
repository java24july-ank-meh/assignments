package com.revature.assignments;

public class Question2 {

	public static void main(String[] args) {

		System.out.println(Fibo(25));
		Fibo2(25);
	}
	
	// prints specific number in the squence
	public static int Fibo(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;
		return ((Fibo(num - 1) + Fibo(num - 2)));
	}
	
	
	// this one prints a list
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

}
