package com.revbank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * A little class for wrapper functions to allow System.console() to be
 * used inside IDEs
 */
public class ConsoleDevice {
	
	
	public char[] readPassword(String str, Object... args) throws IOException {
		if (System.console() != null) {
			return System.console().readPassword(str, args);
		} else return this.readLine(str, args).toCharArray();
	}
	
	public String readLine(String str, Object... args) throws IOException{
		if (System.console() != null) {
			return System.console().readLine(str, args);
		} else {
			System.out.print(String.format(str, args));
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			return bufr.readLine();
		}
	}
}
