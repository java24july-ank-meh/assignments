package com.revature.q15;

public class MathableImpl implements Mathable{

	@Override
	public double addition(double x, double y) {
		
		return x+y;
	}

	@Override
	public double subtraction(double x, double y) {
		
		return x-y;
	}

	@Override
	public double multiplication(double x, double y) {
		
		return x*y;
	}

	@Override
	public double division(double x, double y) {
		if(y==0.0) {throw new ArithmeticException();}
		return x/y;
	}

}
