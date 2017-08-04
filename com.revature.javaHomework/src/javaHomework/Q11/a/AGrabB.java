package javaHomework.Q11.a;
//import package with class b
import javaHomework.Q11.b.*;

public class AGrabB {

	public static void main(String[] args) {
		//access and print variables from class b
		System.out.println("Msg: " + B.getMsg() + '\n' + "Float1 is: " + B.getFloat1() + " and Float 2 is: " + B.getFloat2());

	}

}
