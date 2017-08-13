package com.doubletrouble.dao;

import java.io.IOException;
import java.util.List;

import com.doubletrouble.domain.Reimbursements;
import com.doubletrouble.domain.User;

public interface CHERSDao {
		public String getReciept(int id);
		public List<Reimbursements> viewPending();
		public List<Reimbursements> viewResolved();
		public List<Reimbursements> viewAllReimbs();
		public void resolveRequest(int r_id, int setState, int resolverID);
		public List<User> viewAllEmps();
		public List<Reimbursements> viewEmpReimbRequests(int id);
		
}
