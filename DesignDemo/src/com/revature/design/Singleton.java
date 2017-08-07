package com.revature.design;

public class Singleton {
	private static Singleton object;
	private static Object Lock = new Object();
	
	private Singleton() {
		
	}
	
	public static Singleton getSingletonObject() {
		synchronized(Lock) {
			if(object == null) {
				object = new Singleton();
			}
		}
		return object;
	}
	
}
