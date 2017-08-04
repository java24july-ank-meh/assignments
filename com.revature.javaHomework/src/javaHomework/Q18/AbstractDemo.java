package javaHomework.Q18;


abstract class AbOps {
	abstract boolean isUpper(String str);
	abstract String toUpper(String str);
	abstract int foo(String str); //convert input string to int and add 10 then return result
	abstract void msg();
}

public class AbstractDemo{
	
	//class that extends AbOps
		static class Ops extends AbOps{

			@Override
			void msg() {
				System.out.println("Hi!");
				
			}

			@Override
			boolean isUpper(String str) {
				for(int i = 0; i<str.length(); i++) {
					if( Character.isUpperCase( str.charAt(i) ) == false ) {
						return false;
					}
				}
				return true;
			}

			@Override
			String toUpper(String str) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i<str.length(); i++) {
					char c = str.charAt(i);
					if( Character.isUpperCase(c) == true ) {
						sb.append(c);
					}
					else {
						sb.append( Character.toUpperCase(c) );
					}
				}
				return sb.toString();
			}

			@Override
			int foo(String str) {
				int i = Integer.valueOf(str);
				return i+10;
			}
		}
		
	public static void main(String[] args) {

		Ops o = new Ops();
		
		//test Methods
		o.msg();
		System.out.println("is String \"uPPer\" uppercase? : " + o.isUpper("uPPer") );
		System.out.println("is String \"UPPER\" uppercase? : " + o.isUpper("UPPER") );
		System.out.println("is String \"lower\" uppercase? : " + o.isUpper("lower") );
		System.out.println("convert String \"lower\" to uppercase : " + o.toUpper("lower") );
		System.out.println("convert string \"13\" to t and add 10 to it : " + o.foo("13"));
	}

}

