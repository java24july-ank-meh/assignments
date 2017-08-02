package save_palindromes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SavePalindromes {

	
	public static ArrayList<String> filterPalindromes(ArrayList<String> strings) {
	
		return new ArrayList<String>(strings.stream()
										.filter(str -> {
											StringBuilder sb = new StringBuilder(str);
											sb.reverse();
					
											return str.equals(sb.toString());
										})
										.collect(Collectors.toList()));
		
	}
	
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<>(Arrays.asList("karan", "madam", "tom", "civic", 
																   "radar", "sexes", "jimmy", "kayak", 
																   "john", "refer", "billy", "did"));
		
		System.out.println(strings);
		System.out.println(filterPalindromes(strings));	
	
	}

}
