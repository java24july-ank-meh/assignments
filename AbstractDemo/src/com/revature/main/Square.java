package com.revature.main;

public class Square  extends Rectangle{
	public Square(){
		
	}
	
	public Square(double height) {
		super(height, height);
	}
	
	public Square(double height, double width) {
		this(height);
	}
	
	@Override 
	public String toString() {
		return "Square[] Height = " + getHeight();
	}
	
}
