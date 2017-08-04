package com.Revature.EvenNums;

public class Showeven {
	public void printeven(){
		int[] numbers = new int[100];
		System.out.println("Printing Evens:");
		for(int i=0;i<100;i=i+2){
			numbers[i] = i+1;
			numbers[i+1]= i+2;
			System.out.print(numbers[i+1] + " ");
		}
	}
}
