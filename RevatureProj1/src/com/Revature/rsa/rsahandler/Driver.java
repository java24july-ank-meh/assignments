package com.Revature.rsa.rsahandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Driver {
	long p = 0,q = 0,e = 0;
	long n = 0, phi = 0, d = 0;
	private Scanner scanner;
	private String input = "";
	private String output = "";
	String log = "";
	//THIS WILL ENCRYPT AND STORE TEXT USING RSA using 8-bit numbers
	public Driver(){
		//load P
		scanner = new Scanner(getClass().getResourceAsStream("p.txt"));
		String s = new String();
		while(scanner.hasNextLine()){
		        s = s + scanner.nextLine();
		}
		p = Integer.parseInt(s);
		//load q
		scanner = new Scanner(getClass().getResourceAsStream("q.txt"));
		s = new String();
		while(scanner.hasNextLine()){
		        s = s + scanner.nextLine();
		}
		q = Integer.parseInt(s);
		n = p * q;
		log = log + "\nP = " + p + "\nQ = " + q + "\nN = " + n;
		e = 17;
		phi = (p-1)*(q-1);
		log = log + "\nE = " + e + "\nPhi = " + phi;
		euclid();
	}
	public void euclid(){
		System.out.println("Generating Encryption Keys");
		//Top collumn on both sides
		long u = phi;
		long v = phi;
		//w is left, x is right
		long w = e;
		long x = 1;
		//y and z hold results
		long y = 0,z=0;
		//3rd row
		while (y!= 1){
			log = log + "\nLooping... \ny = " + y + "\nz = " + z + "\n w = " + w + "\nu = " + u;
			y = (long)((double)(u/w));
			z = x*y;
			y = w*y;
			y = u - y;
			z = v - z;
			log = log + "\nLooping Still..... \ny = " + y + "\nz = " + z + "\n w = " + w;
			while(y < 0)
			{
				y = y + phi;
			}
			while (z < 0)
			{
				z = z + phi;
			}
			if((int)y == 1){break;}
			u = w;
			v = x;
			w = y;
			x = z;
			log = log + "\nLoop End... \ny = " + y + "\nz = " + z + "\n w = " + w;
		}
		
		d = z;
		log = log + "\nD = " + d;
	}
	public void encrypt(String entered){
		input = entered;
		EncryptRSASmall encryptthis = new EncryptRSASmall(n, e, input);
		output = encryptthis.output;

		input = "";
	}
	public void decrypt(String entered){
		input = entered;
		DecryptRSASmall decryptthis = new DecryptRSASmall(n, d, input);
		decryptthis.decryptrsa();
		output = decryptthis.output;

		input = "";
		
	}
	public String getOutput(){
		return output;
	}
}
