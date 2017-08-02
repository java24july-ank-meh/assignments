package com.revature.q12;

public class Q12 {
	
	public static void main(String[] args) {
		int[] arr = new int[100];
		fillArray(arr);
		
		for(int i: arr) {
			if(i%2==0) {System.out.println(i);}
		}
	}

	public static void fillArray(int[] arr) {
		for(int i=1; i<=arr.length; i++) {
			arr[i-1] = i;
		}
	}
}
