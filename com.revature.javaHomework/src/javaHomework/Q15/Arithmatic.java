package javaHomework.Q15;


interface SimpleOperations {
	int division(int x, int y);
	int multiplication(int x, int y);
	int subtraction(int x, int y);
	int addition(int x, int y);
}

public class Arithmatic{
	
	//class that implements SimpleOperations
		static class Operations implements SimpleOperations{
			@Override
			public int addition(int x, int y) {
				return x+y;
			}

			@Override
			public int subtraction(int x, int y) {
				return x-y;
			}

			@Override
			public int multiplication(int x, int y) {
				return x*y;
			}

			@Override
			public int division(int x, int y) {
				return x/y;
			}
		}
		
	public static void main(String[] args) {
		int x = 10;
		int y = 5;
		Operations o = new Operations();
		
		//test Operations
		System.out.println(x + " + " + y + " = " + o.addition(x,y) );
		System.out.println(x + " - " + y + " = " + o.subtraction(x,y) );
		System.out.println(x + " * " + y + " = " + o.multiplication(x,y) );
		System.out.println(x + " / " + y + " = " + o.division(x,y) );

	}

}
