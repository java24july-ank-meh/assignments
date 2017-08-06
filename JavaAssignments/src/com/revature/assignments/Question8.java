package com.revature.assignments;

import java.util.ArrayList;
import java.util.Collections;

public class Question8 {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> pally = new ArrayList<String>();
		Collections.addAll(words, "karen", "madam", "tom", "civic", "radar", "sexes", "Jimmy", "kayak", "John", "refer", "billy", "did");
		
		SortStrings(words, pally);
		
		
		for(int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
		System.out.println(" ");
		System.out.println(" ");
		for(int i = 0; i < pally.size(); i++) {
			System.out.println(pally.get(i));
		}
		System.out.println(words.size());
		System.out.println(pally.size());
	}
	
	public static void SortStrings(ArrayList<String> words, ArrayList<String> pally){
		for(int i = words.size()-1; i>=0;i--){
			String word = words.get(i);
			for(int j = 0; j <=(word.length()/2); j++){
				if(j == word.length()/2) {
					pally.add(word);
					words.remove(i);
					break;	
				}
				if(word.charAt(j) == word.charAt((word.length()-j)-1)) {
					continue;
				}
				if(word.charAt(j) != word.charAt((word.length()-j)-1)) {
					break;
				}
			}	
		}
	}

}
