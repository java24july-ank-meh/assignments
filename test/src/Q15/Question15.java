package Q15;

public class Question15 implements Question15Interface {

	/*
	 * Q15 Write a program that defines an interface having the following methods: 
	 * addition, subtraction, multiplication, and division. Create a class that 
	 * implements this interface and provides appropriate functionality to carry 
	 * out the required operations.
	 */
	
	public double addition(double x, double y) {
		return x+y;
	}
	
	public double subtraction(double x, double y) {
		return x-y;
	}
	
	public double multiplication(double x, double y) {
		return x*y;
	}
	
	public double division(double x, double y) {
		return x/y;
	}
	
	public static void main(String[] args) {
		Question15 obj = new Question15();
		System.out.println(obj.addition(9,10));
		System.out.println(obj.subtraction(19,66));
		System.out.println(obj.multiplication(8989,48579));
		System.out.println(obj.division(4, 8479));
	}

}

interface Question15Interface{
	public double addition(double x, double y);
	public double subtraction(double x, double y);
	public double multiplication(double x, double y);
	public double division(double x, double y);
}
