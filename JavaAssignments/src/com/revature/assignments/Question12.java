package com.revature.assignments;

import java.util.ArrayList;

public class Question12 {

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0; i<=100;i++) {
			nums.add(i);
		}
		Evens(nums);
		
	}
	public static void Evens(ArrayList<Integer> nums) {
		for(int cur : nums) {
			if(cur %2 == 0) {
				System.out.println(cur + " is even.");
			}
		}
	}
}
