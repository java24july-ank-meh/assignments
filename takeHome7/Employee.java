package revature.takeHome7;

public class Employee {

	@Override
	public String toString() {
		return "Employee [name=" + name + ", dpt=" + dpt + ", age=" + age + "]";
	}

	private String name;
	private String dpt;
	private int age;

	public Employee(String name, String department, int age) {
		this.name = name;
		this.dpt = department;
		this.age = age;

	}

	public Employee() {

	}

	public String getName() {
		return name;
	}

	public String getDpt() {
		return dpt;
	}

	public int getAge() {
		return age;
	}

}
