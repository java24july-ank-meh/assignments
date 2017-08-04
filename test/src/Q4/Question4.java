package Q4;

public class Question4 {

	/*
	 * Q4 Write a program to compute N facotorial
	 */
	public static int nfactorial(int n) {
		if(n<0)
			return -1;
		if(n==1)
			return n;
		return n*nfactorial(n-1);
	}
	
	public static void main(String args[]) {
		System.out.println(nfactorial(4));
		System.out.println();
		System.out.println(nfactorial(-5));
	}
	
}
