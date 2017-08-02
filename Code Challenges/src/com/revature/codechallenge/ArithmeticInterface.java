package com.revature.codechallenge;

interface Mathable {
	int add(int value);
	int subtract(int value);
	int multiply(int value);
	int divide(int value);
}

class MathTest implements Mathable {
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public MathTest (int value) {
		this.value = value;
	}

	@Override
	public int add(int value) {
		return this.value += value;
	}

	@Override
	public int subtract(int value) {
		return this.value -= value;
		
	}

	@Override
	public int multiply(int value) {
		return this.value *= value;
		
	}

	@Override
	public int divide(int value) {
		return this.value /= value;
		
	}
	
}


public class ArithmeticInterface {
	public static void main(String[] args) {
		MathTest mt = new MathTest(2);
		MathTest mt2 = new MathTest(10);
		System.out.println(mt.add(3));
		System.out.println(mt2.divide(mt.getValue()));
	}
}
