package com.revature.team201.reimbursement.users;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class Employee extends User{
	
	public Employee(Integer uId, String username, String firstName, String lastName, String eMail) {
		super(uId, username, firstName, lastName, eMail, Role.EMPLOYEE);
	}

	@Override
	public String toString() {
		return "Employee [getUserId()=" + getUserId() + ", getUsername()=" + getUsername() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", geteMail()=" + geteMail()
				+ ", getUserRole()=" + getUserRole() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + " ]";
	}
	
}
