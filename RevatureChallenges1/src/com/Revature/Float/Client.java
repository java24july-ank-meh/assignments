package com.Revature.Float;

import com.Revature.Float2.HostFloat;

public class Client {
	public String access(){
		HostFloat host = new HostFloat();
		float a = host.getA();
		float b = host.getB();
		String returned = "First: " + a + "\nSecond: " + b;
		return returned;
	}
}
