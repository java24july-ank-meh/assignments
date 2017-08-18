package com.revature.Q13;

class Triangle {
	public static void main(String[] args) {
		String print;
		boolean zero = true;
		for (int i=0; i<=4; i++) {
			for (int j=0; j<i; j++) {
				if( zero) {
					print = "0";
					zero = false;
				} else {
					print = "1";
					zero = true;
				}
				System.out.print(print + " ");
			}
		System.out.print("\n");
		}
	}
}

