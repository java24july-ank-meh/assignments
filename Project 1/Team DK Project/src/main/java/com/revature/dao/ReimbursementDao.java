package com.revature.dao;

import java.util.List;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;

public interface ReimbursementDao {

	public List<Reimbursement> readAllReimb();
	public List<Reimbursement> readReimbByUser(User u, String s);
	
	public Reimbursement readReimb(int rID);
	//public Reimbursement readReimb(Reimbursement r);
	
	public void createReimb(Reimbursement r);
	
	public void	 updateReimbReg(Reimbursement r);
	public void	 updateReimbReciept(Reimbursement r);
	public void	 updateReimbResolved(Reimbursement r);
	
	//public void deleteReimb(Reimbursement r);
	public void deleteReimb(int rID);
	
}
