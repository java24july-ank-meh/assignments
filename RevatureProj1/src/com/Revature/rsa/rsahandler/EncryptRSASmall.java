package com.Revature.rsa.rsahandler;

import java.math.BigInteger;

public class EncryptRSASmall {
	long n = 0;
	long e = 0;
	private String input = "";
	long[] input1;
	long[] output1;
	String log = "";
	String output = "";
	public EncryptRSASmall(long n1, long e1, String input2){
		n = n1;
		e = e1;
		input = input2;
		input1 = new long[input.length()];
		output1 = new long[input.length()];
		for(int i=0;i<input1.length;i++){
			input1[i] = (int)input.charAt(i);
		}
		encryptrsa();
	}
	public void encryptrsa(){
		long temp;
		BigInteger eval, bi1;
		BigInteger modu = new BigInteger(Long.toString(n));
		for(int i=0;i<input1.length;i++){
			temp = input1[i];
			eval = new BigInteger(Long.toString(temp));
			bi1 = eval.pow((int) e);
			eval = bi1.mod(modu);
			output1[i] = eval.longValue();
			output = output + " " + output1[i];
		}
		
	}
}
