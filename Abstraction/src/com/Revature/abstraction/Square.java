package com.Revature.abstraction;

public class Square extends Rectangle {
	private double width;
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Square() {
		// TODO Auto-generated constructor stub
	}

	public Square(double width) {
		super(width, width);
		// TODO Auto-generated constructor stub
	}
	public Square(double width, double height)
	{
		super(width,height);
	}

}
