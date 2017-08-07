package question18;

public class ChildClass extends SuperClass{

	@Override
	public boolean hasUppers(String s) {
		return !s.equals(s.toLowerCase());
	}

	@Override
	public String toUpper(String s) {
		return s.toUpperCase();
	}

	@Override
	public void intPlus10(String s) {
		System.out.println("intPlus10("+ Integer.parseInt(s) + ") = " + (Integer.parseInt(s) + 10));
	}
	
}
