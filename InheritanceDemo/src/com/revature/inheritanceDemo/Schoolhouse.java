package com.revature.inheritanceDemo;

public class Schoolhouse extends Building{
	public Schoolhouse() {};
	public Schoolhouse(int height, String material) {
		
	}
	@Override
	public void maintain() {
		
	}
	public static void main(String[] args) {
		Building b = new Schoolhouse();
	}
}
