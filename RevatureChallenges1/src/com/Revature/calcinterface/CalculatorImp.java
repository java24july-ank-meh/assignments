package com.Revature.calcinterface;

public class CalculatorImp implements Calculator{

	@Override
	public int add(int x, int y) {
		return x+y;
	}

	@Override
	public int subtract(int x, int y) {
		return x-y;
	}

	@Override
	public int multiply(int x, int y) {
		return x*y;
	}

	@Override
	public int divide(int x, int y) {
		return x/y;
	}

	@Override
	public void exMethod() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
