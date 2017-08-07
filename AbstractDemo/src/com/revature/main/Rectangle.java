package com.revature.main;

public class Rectangle extends Shape {
	private double height;
	private double width;

	public Rectangle() {
		super();
		this.height = height;
		this.width = width;
	}

	public Rectangle(double height, double width) {
		setHeight(height);
		setWidth(width);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double calculateArea() {
		return height * width;
	}
	
	public double perimeter() {
		return (height+width)*2;
	}
	
	public double perimeter(double height, double width) {
		return (height + width)*2;
	}
	
	public int perimeter(int height, int width) {
		return (height + width)*2;
	}
	
	public void perimeter(Integer height, Integer width) {
		System.out.println("Integer version");
	}
}
