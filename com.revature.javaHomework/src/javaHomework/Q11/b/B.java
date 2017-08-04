package javaHomework.Q11.b;

//simple class with three final variables and three respective getter methods
public class B {
	final static float float1 = (float) 100.001;
	final static float float2 = (float) 200.002;
	final static String msg = "I'm from class B in package javaHomework.Q11.b";

	public static float getFloat1() {
		return float1;
	}

	public static float getFloat2() {
		return float2;
	}

	public static String getMsg() {
		return msg;
	}
}
