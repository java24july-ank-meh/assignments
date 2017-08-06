package com.revature.assignments;

import java.util.ArrayList;

public class Question9 {

	public static void main(String[] args){
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0; i<=100;i++) {
			nums.add(i);
		}
		Primes(nums);
	}
	
	public static void Primes(ArrayList<Integer> nums) {
		for(int i = nums.size()-1; i>0; i--) {
			for(int j = 2; j<=(int) nums.get(i); j++) {
				if(i == 2) {
					System.out.println((int) nums.get(i)+" is prime");
					break;
				}
				if((int) nums.get(i)%j == 0) {
					
					break;
				}else if(j ==(int) nums.get(i)-1) {
					System.out.println((int) nums.get(i)+ " is prime");
					break;
				}
			}
		}
	}
}
