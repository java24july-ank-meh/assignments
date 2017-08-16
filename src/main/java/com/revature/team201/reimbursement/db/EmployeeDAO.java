package com.revature.team201.reimbursement.db;

import java.util.ArrayList;
import com.revature.team201.reimbursement.users.Employee;
import com.revature.team201.reimbursement.users.Reimbursement;

public interface EmployeeDAO {
	
	public Employee getEmployeeInformation(Integer employeeId);
	public void updateEmployeeInformation(Employee employee, String password);
	public void submitReimbursement(Reimbursement reimbursement);
	public ArrayList<Reimbursement> getPendingReimbursements(Integer userId);
	public ArrayList<Reimbursement> getResolvedReimbursements(Integer userId);
}