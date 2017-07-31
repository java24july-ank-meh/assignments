package Algorithms;
//1) Write a program to display the first 25 Fibonacci numbers beginning at 0.
//2) Write a program to compute N factorial.
public class algo {
	/*
	 * Fibonacci calculator, returns -1 if error
	 */
	public static int first25fib(int n) {
		if(n<0)
			return -1;
		if(n<=2)
			return 1;
		return first25fib(n-2)+first25fib(n-1);
		
	}
	
	public static void first25fib() {
		int[] arr = new int[25];
		arr[0]=1;
		arr[1]=1;
		for(int i=2;i<arr.length;i++) {
			arr[i]=arr[i-2]+arr[i-1];
		}
		System.out.println(arr[24]);
	}
	/*
	 * nfactorial calculator, returns -1 if error
	 */
	public static int nfactorial(int n) {
		if(n<0)
			return -1;
		if(n==1)
			return n;
		return n*nfactorial(n-1);
	}
	
	public static void main(String args[]) {
		System.out.println(first25fib(25));
		System.out.println(first25fib(0));
		System.out.println(first25fib(-56));
		first25fib();
		System.out.println(nfactorial(4));
		System.out.println(nfactorial(-5));
	}
}
