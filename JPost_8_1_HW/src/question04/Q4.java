package question04;

public class Q4 {
	/*
	 * Question 4: Write a program to compute N factorial
	 */
	public static void main(String[] args) {
		//Q4 Test
		System.out.println("Test\n6! = "+nFact(6));
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
