package com.revature.Q10;

public class TernaryOps {

	public static void main(String[] args) {
		System.out.println(findMin(13,9));
	}
	public static int findMin(int num1, int num2) {
		return num1 > num2 ? num2 : num1; 
	}
}
