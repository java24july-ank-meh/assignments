package com.Revature.oddoreven;

public class OddorEven {
	public String returnType(int x){
		
		while(x >=2){
			x = x-2;
		}
		if(x==0){
			return "Even";
		}
		
		return "Odd";
	}
}
