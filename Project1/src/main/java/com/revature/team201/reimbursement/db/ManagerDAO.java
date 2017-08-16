package com.revature.team201.reimbursement.db;

import java.util.ArrayList;
import java.util.List;
import com.revature.team201.reimbursement.users.Employee;
import com.revature.team201.reimbursement.users.Reimbursement;
import com.revature.team201.reimbursement.users.Status;

public interface ManagerDAO {
	public void updateStatusOnReimbursement(Integer reimbursementId, Status status);
	public ArrayList<Employee> getAllEmployees();
	public ArrayList<Reimbursement> getAllPendingReimbursements();
	public ArrayList<Reimbursement> getAllResolvedReimbursements();
	public ArrayList<Reimbursement> getEmployeeReimbursements(Integer empId);
}
