package com.Revature.abstraction;

public class Rectangle extends Shapes {
	private double width;
	private double length;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Rectangle() {
		// TODO Auto-generated constructor stub
	}
	public Rectangle(double width, double length){
		this.width = width;
		this.length = length;
	}

	@Override
	public double calculateArea() {
		// TODO Auto-generated method stub
		return length * width;
	}
	public double calculatePerimeter(){
		return (length+width)*2;
	}

}
