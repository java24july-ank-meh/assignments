package com.revature.hi;
import java.net.*;
public class sayHello {
	
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		
		URI uri = new URI("https://mail.google.com/mail/u/0/#inbox"); //Some instantiated URL object
		URI uri1 = new URI ("https://revaturepro.slack.com/messages/G6CUJAVDK/");
		URI uri2 = new URI("https://docs.google.com/document/d/1DMNwgxdFwv7DJy83trV3jGjQQBSLksduyNpZVqntC5g/edit");

		try{
			java.awt.Desktop.getDesktop().browse(uri);
			java.awt.Desktop.getDesktop().browse(uri1);
			java.awt.Desktop.getDesktop().browse(uri2);


		}
		
		catch(Exception e) {
			System.out.println("cannot reach website");
			System.gc();
		}
	}
}
