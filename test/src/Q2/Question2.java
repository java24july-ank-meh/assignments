package Q2;

public class Question2 {
	
	/*
	 * Q2 Write a program to display first 25 Fibonacci numbers beginning at 0. 
	 * (recursive version)
	 */
	public static int first25fib(int n) {
		if(n<0)
			return -1;
		if(n<=2)
			return 1;
		return first25fib(n-2)+first25fib(n-1);
		
	}
	
	/*
	 * Q2 Write a program to display first 25 Fibonacci numbers beginning at 0. 
	 * (non-recursive version)
	 */
	public static void first25fib() {
		int[] arr = new int[25];
		arr[0]=1;
		arr[1]=1;
		for(int i=2;i<arr.length;i++) {
			arr[i]=arr[i-2]+arr[i-1];
		}
		System.out.println(arr[24]);
	}

	public static void main(String[] args) {
		System.out.println(first25fib(25));
		System.out.println();
		System.out.println(first25fib(0));
		System.out.println();
		System.out.println(first25fib(-56));
		System.out.println();
		first25fib();

	}

}
