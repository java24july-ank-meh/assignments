package com.Revature.factorial;

public class Factorial {
	public int calculate(int x){
		int value = 1;
		
		for(int i=1; i<=x; i++){
			value = value*i;
		}
		
		return value;
	}
}
