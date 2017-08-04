package javaHomework.Q7;

public class TermaryMin {
	
	public static int MinVal(int a, int b) {
		return (a < b) ? a : b;
	}
	
	public static void main(String[] args) {
		
		int a = 5;
		int b = 10;
		
		int min1 = MinVal(a,b);
		int min2 = MinVal(b,a);
		
		//test that MinVal's parameter's order doesn't matter
		assert min1 == min2;
		
		//test method and print result
		if( (a<b && min1 == a) || (a>b && min1 == b) ) {
			System.out.println("The minimum value of " + a + " and " + b + " is: " + min1);
		}
		

	}
}
