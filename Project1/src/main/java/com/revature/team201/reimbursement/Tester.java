package com.revature.team201.reimbursement;

import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.users.Employee;

public class Tester 
{
    public static void main( String[] args )
    {
	    	Employee employee = new Employee(1,"jpost","josh","post", "jpost@com");
	    	EmployeeDAOImpl edao = new EmployeeDAOImpl();
	    	edao.updateEmployeeInformation(employee, "password");
    }
}
