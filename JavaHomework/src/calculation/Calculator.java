package calculation;

interface Calculators {
	
	public double addition(double x, double y);
	public double subtraction(double x, double y);
	public double multiplication(double x, double y);
	public double division(double x, double y);
}

public class Calculator implements Calculators {

	public static void main(String[] args) {
		Calculator calc = new Calculator(); 
		
		System.out.println("2 * 3 = " + calc.multiplication(2, 3));
		System.out.println("(3 + 4) * 2 = " + calc.multiplication(calc.addition(3, 4), 2));

	}

	@Override
	public double addition(double x, double y) {
		return x + y;
	}

	@Override
	public double subtraction(double x, double y) {
		return x - y;
	}

	@Override
	public double multiplication(double x, double y) {
		return x * y;
	}

	@Override
	public double division(double x, double y) {
		return x / y;
	}

}
