package com.revature.main;

public class Circle extends Ellipse{

	private double radius;
	final private double PI = 3.14159;
	
	public Circle() {
		super();
	}
	
	public Circle(double radius) {
		setRadius(radius);
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double calculateArea() {
		return PI*radius*radius;
	}
	
}
