package com.revature.mathstuff;

public class MathStuff
{

	public static void main(String[] args) 
	{
		System.out.println(fact(14));
		fib(50);
	}
	
	public static void fib(int depth)
	{
		long a = 0;
		long b = 1;
		long c;
		System.out.println(a);
		System.out.println(b);
		for(int i = 2; i < depth; i++)
		{
			c = a+b;
			System.out.println(c);
			a=b;
			b=c;
		}
	}
	
	public static long fact(int input)
	{
		if(input == 0)
			return 1;
		long result = 1;
		for(int i = 2; i <= input; i++)
		{
			result *= i;
		}
		return result;
	}

}
