package question20;

public class Person {
	private String fName;
	private String lName;
	private int age;
	private String state;
	
	public Person() {}
	public Person(String first, String last, int a, String s) {
		fName = first;
		lName = last;
		age = a;
		state = s;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
