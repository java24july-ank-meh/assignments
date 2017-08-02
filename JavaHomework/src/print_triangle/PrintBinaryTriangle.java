package print_triangle;

public class PrintBinaryTriangle {

	public static void printTriangle(int upto) {
		
		String start = "0";
		
		for (int i = 1; i <= upto; i++) {
			for (int j = i; j > 0; j--) {
				System.out.print(start);
				start = start.equals("0") ? "1" : "0";
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
	
		printTriangle(4);
		
	}

}
