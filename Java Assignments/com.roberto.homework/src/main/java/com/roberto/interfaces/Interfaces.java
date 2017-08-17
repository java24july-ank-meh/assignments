package com.roberto.interfaces;

interface Interfaces {
	//an interface only has unimplemented, abstract methods and variables with the modifiers static, final and public.

	abstract double addition(double a, double b);
	abstract double subtraction(double a, double b);
	abstract double multiplication(double a, double b);
	abstract double division(double a, double b);
	
	static int added = 1;
	public int add = 2;
	final int mer = 3;
}
