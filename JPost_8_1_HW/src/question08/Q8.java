package question08;
import java.util.ArrayList;
import question03.Q3;

public class Q8 {
	/*
	 * Question 8: Write a program that stores the following
	 * strings in an ArrayList and saves all the palindromes
	 * in another ArrayList: "karan", "madam", "tom", "civic",
	 * "radar", "sexes", "jimmy", "kayak", "john", "refer",
	 * "billy", "did"
	 */
	public static void main(String[] args){
		ArrayList<String> forward = new ArrayList<>();
		forward.add("karan");
		forward.add("madam");
		forward.add("tom");
		forward.add("civic");
		forward.add("radar");
		forward.add("sexes");
		forward.add("jimmy");
		forward.add("kayak");
		forward.add("john");
		forward.add("refer");
		forward.add("billy");
		forward.add("did");
		ArrayList<String> backward = new ArrayList<>();
		for(int x = 0; x < forward.size(); x++) {
			backward.add(Q3.reverse(forward.get(x)));
		}
		System.out.print("Test\nForward ArrayList -> ");
		for(int x = 0; x < forward.size(); x++) {
			System.out.print(forward.get(x)+", ");
		}
		System.out.print("\nBackward ArrayList -> ");
		for(int x = 0; x < backward.size(); x++) {
			System.out.print(backward.get(x)+", ");
		}
		System.out.println();
	}
}
