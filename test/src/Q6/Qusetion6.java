package Q6;

public class Qusetion6 {
	/*
	 * Q6 Determine if an integer is even without using the modulus operator (%)
	 */
	public static boolean isEven(int n) {
		if(n>=0) {
			while(n>0)
				n-=2;
			if(n==0)
				return true;
			
			return false;
		}
		while(n<0)
			n+=2;
		
		if(n==0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isEven(-6));
		System.out.println();
	}

}
