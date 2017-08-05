package com.Revature.rsa.rsahandler;

import java.math.BigInteger;

public class DecryptRSASmall {
	long n = 0;
	long d = 0;
	String input = "";
	long[] input1;
	long[] output1;
	String log = "";
	String output = "";
	DecryptRSASmall(long n1, long d1, String input2)
	{
		n = n1;
		d = d1;
		input = input2;
		//tokenize input.
		tokenize(input2);
	}
	private void tokenize(String input2)
	{
		log = log + "\nTokenizing input...";
		String[] tempinput = input2.split(" ");
		input1 = new long[tempinput.length];
		output1 = new long[input1.length];
		log = log + "\nInput is: ";
		for(int i=0; i< tempinput.length;i++)
		{
			//turn number strings to ints.
			input1[i] = Integer.parseInt(tempinput[i]);
			log = log + " " + input1[i];
		}
	}
	public void decryptrsa()
	{
		log = log + "\nDecryption starting...";
		long temp = 0;
		System.out.println("\nN is: " + n + "\nD is: " + d);
		BigInteger eval, bi1;
		BigInteger modu = new BigInteger(Long.toString(n));
		for(int i=0; i<input1.length;i++)
		{
			//log = log + "\nDecrypting character at: " + i;
			temp = input1[i];
			eval = new BigInteger(Long.toString(temp));
			bi1 = eval.pow((int) d);
			eval = bi1.mod(modu);
			output1[i] = eval.longValue();
			output = output + (char)output1[i];
			log = log + "\nCurrent output is: " + output;
		}
		System.out.println(log);
	}
}
