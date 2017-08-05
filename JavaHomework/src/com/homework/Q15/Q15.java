package com.homework.Q15;

public class Q15 implements MathInterface {

	public static void main(String[] args) {
		
		Q15 math = new Q15();
		System.out.println(math.subtract(5, 3));
		System.out.println(math.sum(5, 4));
	}

	@Override
	public double sum(double x, double y) {
		return x+y;
	}

	@Override
	public double subtract(double x, double y) {
		return x-y;
	}

	@Override
	public double multiply(double x, double y) {
		return x*y;
	}

	@Override
	public double divide(double x, double y) {
		if(y != 0)
			return x/y;
		return 0;
	}

}
