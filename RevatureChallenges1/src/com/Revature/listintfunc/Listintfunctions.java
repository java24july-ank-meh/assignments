package com.Revature.listintfunc;

import java.util.ArrayList;

public class Listintfunctions {
	static boolean isPrime(int n) {
	    for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	public void function(ArrayList L){
		//Add Even
		System.out.println("EvenSum: ");
		int temp = 0;
		for(int i=1; i<L.size(); i=i+2){
			temp = temp + (int)L.get(i);
		}
		System.out.println(temp);
		
		//Add Odd
		temp = 0;
		System.out.println("OddSum: ");
		for(int i=0; i<L.size(); i=i+2){
			temp = temp + (int)L.get(i);
		}
		System.out.println(temp);
		
		System.out.println("RemoveNonPrime: ");
		for(int i=0; i<L.size(); i++){
			if(!isPrime((int)L.get(i))){
				L.remove(i);
				--i;
			}
		}
		System.out.println(L.toString());
	}
}
