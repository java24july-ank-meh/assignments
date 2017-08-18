package com.revature.Q12;

public class EvenNumbers {

	public static void main(String[] args) {
		int[] nums = new int[100];
		for(int i=0; i<100; i++) {
			nums[i] = i+1;
		}
		for(int n: nums) {
			if(n%2==0) {
			System.out.print(n +", ");
			}
		}
	}
}
