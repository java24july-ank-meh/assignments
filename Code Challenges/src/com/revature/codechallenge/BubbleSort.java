package com.revature.codechallenge;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] numsToSort = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		
		numsToSort = bubbleSort(numsToSort);
		
		System.out.println(Arrays.toString(numsToSort));
	}
	
	public static int[] bubbleSort(int[] nums) {
		boolean check;
		do {
			check = false;
			
			for (int i = 0; i < nums.length-1; ++i) {
				if (nums[i] > nums[i+1]) {
					int temp = nums[i+1];
					nums[i+1] = nums[i];
					nums[i] = temp;
					check = true;
				}
			}
			
		} while(check);
		
		return nums;
	}
}
