package com.revature.assignments;

public class Question15 implements Question15Interface {

	public static void main(String[] args) {
		
	}
	@Override
	public void addition(int num1, int num2) {
		System.out.println(num1+" + " +num2 +" = "+(num1+num2));
		
	}

	@Override
	public void subtraction(int num1, int num2) {
		System.out.println(num1+" - " +num2 +" = "+(num1-num2));
		
	}

	@Override
	public void multiplication(int num1, int num2) {
		System.out.println(num1+" * " +num2 +" = "+(num1*num2));
		
	}

	@Override
	public void division(int num1, int num2) {
		System.out.println(num1+" / " +num2 +" = "+(num1/num2));
		
	}

}
