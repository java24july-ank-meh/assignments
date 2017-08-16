package com.revature.team201.reimbursement.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Dispatcher {
	public void dispatch(String uri, HttpServletRequest req, HttpServletResponse resp);
}
