package com.revature.exceptions;

public class BAExceptions extends Exception{
	
	/*
	 * Parent of all apps transactions
	 */
	
	private String msg1;
	private String msg2;
		
	//-------------------------
	
	public BAExceptions() {
		msg1 = msg1 = "This is the head class of all Banking App exceptions"
				+ "..please try again later, sorry for disturbance..";
		msg2 = "Head Exception: BAException, sorry/failure";
	}
	
	public BAExceptions(String message) {
		super(message);
		
		msg1 = msg1 = "This is the head class of all Banking App exceptions"
				+ "..please try again later, sorry for disturbance..";
		msg2 = "Head Exception: BAException, sorry/failure";
	}
	/*
	public String getMessage() {
		return "This is the head class of all Banking App exceptions"
				+ "..please try again later, sorry for disturbance..";
	}
	
	public String toString() {
		return "Head Exception: BAException, sorry/failure";
	}
	*/
	//-----------------

	public String getMsg1() {
		return msg1;
	}

	public String getMsg2() {
		return msg2;
	}	

}
