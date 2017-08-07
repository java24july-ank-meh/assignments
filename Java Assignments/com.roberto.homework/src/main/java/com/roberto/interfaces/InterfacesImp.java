package com.roberto.interfaces;

public class InterfacesImp implements Interfaces{//interfaces have to be implemented whereas abstract classes need to be extended
	//abstract class cannot be instantiated

	public static void main(String[] args) {
		double a, b;
		a = 10;
		b = 5.23;
		
		InterfacesImp inter = new InterfacesImp(); //instantiate this class
		System.out.println(inter.addition(a, b));
		System.out.println(inter.subtraction(a, b));
		System.out.println(inter.multiplication(a, b));
		System.out.println(inter.division(a, b));
		System.out.println(inter.division(a, 0));
	}

	//implement abstract methods. If not all of these methods were to be implemented then this class would have to be abstract
	public double addition(double a, double b) {
		double added = a + b;
		return added;
	}

	public double subtraction(double a, double b) {
		double subtracted = a - b;
		return subtracted;
	}

	public double multiplication(double a, double b) {
		double multi = a * b;
		return multi;
	}

	public double division(double a, double b) {
		double div = 0;
		if(b == 0) {
			System.out.println("Can't divide by 0!");
		}
		else {
			div = a / b;
		}
		return div;
	}

}
