package com.Revature.abstraction;

public class Circle extends Elipses {
	private double radius;
	final private double Pi = 3.1415927;
	public Circle() {
		super();
	}
	public Circle(double rad){
		radius = rad;
	}
	public void setRadius(int i){
		radius = i;
	}
	public double getRadius(){
		return radius;
	}

	@Override
	public double calculateArea() {
		// TODO Auto-generated method stub
		return Pi*radius*radius;
	}

}
