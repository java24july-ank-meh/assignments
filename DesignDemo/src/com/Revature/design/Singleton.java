package com.Revature.design;

public class Singleton {
	private static Singleton object;
	private static Object Lock = new Object();
	
	private Singleton(){
		//Something
	}
	
	public static Singleton getSingletonObject(){
		synchronized(Lock){
			if (object == null){
				object = new Singleton();
			}
		}
		return object;
	}
}
