package com.revature.q6;

public class Q6 {
	
	public static void main(String[] args) {
		System.out.println(isEven(9));
	}
	
	public static boolean isEven(int x) {
		double xd = x; //cast argument as a double so that division will include floating point
		double quotient = xd/2.0; 
		/*divide double version of argument by two. Obvi, if the original arg was even, the 
		 * floating point portion will be 0. Otherwise, it will be 0.5 
		 */
		int quotientFloor = (int) quotient;
		quotient -= quotientFloor; 
		/*
		 * By subtracting the floor of the quotient from the original value, we're left with the
		 * floating point portion.
		 */
		return quotient == 0.0;
	}
}
