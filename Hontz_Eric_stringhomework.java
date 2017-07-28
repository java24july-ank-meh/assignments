package com.revature.stringhomework;

public class stringhomework 
{

	public static String strRev(String str) 
	{
		StringBuilder result = new StringBuilder();
		for(int i = str.length() -1; i >= 0;i--)
		{
			result.append(str.charAt(i));
		}
		return result.toString();
	}
	
	public static String partStr(String str, int index)
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < index; i++)
		{
			result.append(str.charAt(i));
		}
		return result.toString();
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) 
	{
		String test = "Ready Player 1";
		System.out.println(strRev(test));
		System.out.println(partStr(test, 5));
	}
	
	

}
