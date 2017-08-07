package com.revature.hw;

public class Homework {

	public static void main(String[] args) {
		//fibonacci();
		System.out.println(nFact(5));
	}

	public static void fibonacci() {
		fibonacci(0,1,0);
	}
	public static void fibonacci(int first, int second, int count) {
		if(count==25)
			return;
		System.out.println(first);
		fibonacci(second,first+second,count+1);
	}
	
	public static int nFact(int n)
    {
        int result;
        if(n==1)
        		return 1;
        	result = nFact(n-1) * n;
        	return result;
    }
}
