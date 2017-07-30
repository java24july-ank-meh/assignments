package com.revature.fibnum;

public class FibNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sentinal =26;
		int [] fib = new int[sentinal];
		fib[0]=0;
		fib[1]=1;
		
		for(int x=2;x<sentinal;x++) 
		{
			fib[x]=fib[x-1]+fib[x-2];
		}
		for(int x=0; x<sentinal;x++) 
		{
			System.out.println(fib[x]);
		}
	}

}
