package com.roberto.variablesone;

public class AccessVarOne {

	private int ssn; //can only be accessed in this class
	public String name;//can be accessed by any class,sub class and package
	protected String department;//can be accessed in this class, through package and sub class.
	double salary;//default can be accessed in this class and through package but not inherited
	final int contractTime = 2; //this variable is a constant and can't be changed
	static String typeEmp; //this is a class variable. Can be accessed by the class without instantiation. instances share the same copy of this variable.
	final float workedHr = 8.0f;// another constant

	public float getWorkedHr() {
		return workedHr;
	}

	//constructor with parameters
	public AccessVarOne(String name, String department, double salary) {
		super();// used to call the parent constructor
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	//constructor without parameters
	public AccessVarOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int getSsn() {
		return ssn;
	}

	private void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public static String getTypeEmp() {
		return typeEmp;
	}

	public static void setTypeEmp(String typeEmp) {
		AccessVarOne.typeEmp = typeEmp;
	}

	public int getContractTime() {
		return contractTime;
	}
}
