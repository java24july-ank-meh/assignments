package com.roberto.variablestwo;

import com.roberto.variablesone.AccessVarOne;

//every variable and method in AccessVarOne can be accessed except ssn which is private.
//but can be accessed if methods are public.
public class AccessVarOneFromTwo extends AccessVarOne{

	public static void main(String[] args) {
		AccessVarOne emp1 = new AccessVarOne("Roberto Alvarez", "App Development", 9.50);//creates a new object with the values in the parameters 
		
		String name, department;
		double salary;
		int contractTime;
		float workedHr;
		int ssn;
		/*emp1.setSsn(34525634);
		ssn = emp1.getSsn();*/
		//can't get them because getter and setter are private
		
		AccessVarOne.setTypeEmp("Full Time"); //this is how you call static methods
		
		name = emp1.getName();
		department = emp1.getDepartment();
		salary = emp1.getSalary();
		contractTime = emp1.getContractTime();
		workedHr = emp1.getWorkedHr();
		
		
		System.out.println(name + " " + department + " " + AccessVarOne.getTypeEmp() + " " + workedHr + " " + salary + " " + contractTime);
		/*System.out.println(ssn);*/
	}
}
