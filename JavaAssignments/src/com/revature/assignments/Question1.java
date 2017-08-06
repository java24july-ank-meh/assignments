package com.revature.assignments;

public class Question1 {
	public static void main(String[] args) {
		int[] numbers = {1,0,5,6,3,2,3,7,9,8,4};
		BubbleSort(numbers);
		for(int i =0; i < numbers.length; i++){
			System.out.println(numbers[i]); // used to check the order of the list
		}
	}
	public static void BubbleSort(int[] numbers) {
		for(int i=0; i < numbers.length; i++){ // total number of iterations
			for(int j=1; j <numbers.length; j++) { //current position within the iteration
				if(numbers[j-1] > numbers[j]) {
					int temp = numbers[j-1]; // store the value we are changing
					numbers[j-1] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
	}
}
