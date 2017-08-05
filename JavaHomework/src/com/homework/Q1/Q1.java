package com.homework.Q1;

public class Q1 {

	public static void main(String[] args) {
		//initialize array
		int[] arr = {1,0,5,6,3,2,3,7,9,8,4};
		int temp;
		
		//nested for loop to iterate over the array equal to its length
		for(int i = 0; i < arr.length; ++i) {
			for(int j = 0; j < arr.length; ++j) {
				//if the elem at index i is less than that at index j swap them with a temp variable
				if(arr[i] < arr[j]) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				}
			}
		}
		//printout the sorted array
		for(int i = 0; i < arr.length; ++i)
			System.out.print(arr[i] + " ");
	}

}
