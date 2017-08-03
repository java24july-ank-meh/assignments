package com.revature.Fibonacci;

class Fiboacci {
	void fibonacci(int num) {
		int first=0, second=1, next;
		for(int i=0; i<=num; i++) {
			if(i<=1) {
				next = i;
			} else {
				next = first + second;
				first = second;
				second = next;
			}
			System.out.print(next+", ");
		}
		
	}
	public static void main(String[] args) {
		Fiboacci obj = new Fiboacci();
		obj.fibonacci(25);
	}
}
