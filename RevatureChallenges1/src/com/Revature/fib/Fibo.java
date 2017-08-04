package com.Revature.fib;

public class Fibo {
	private String result = "";
	public String fibprintint(int x){
		int current = 1;
		int prev = 0;
		int temp;
		//For reusability, allow user to choose number of fib to call
		for(int i=0;i<x;i++){	
			result = result + Integer.toString(current) + " ";
			temp = current;
			current = current+prev;
			prev=temp;	
		}
		
		
		return result;
	}
}
