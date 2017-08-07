package com.revature.dao;

import com.revature.domain.*;

public interface LoginDAO {
	public boolean login(Login lg);
	public boolean createUser(User u);
	public User getUserInfo(Login lg);
}
