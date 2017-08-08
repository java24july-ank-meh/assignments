package com.revature.q1;

public class BubbleSort {
	public static int[] sort(int[] arr){
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr.length-i; j++) {
				if(arr[j-1] > arr[j]) {
					//swap
					int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		return arr;
	}
}
