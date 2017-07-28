package com.Revature.Exception;


class NewException extends ArithmeticException{
	public String getMessage(){
		return "You can't do that.";
	}
}

public class ExceptionDemo {
	public static String hello(){
		return "Hello";
	}
	
	public static int divide(){
		return 1/0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			throwEx();
			
		} catch(NewException e){
			System.out.println(e.getMessage());
		} catch(Exception e){
			System.out.println("GenericException");
		}

	}
	public static void throwEx() throws NewException{
		throw new NewException();
	}

}
