package com.revature.team201.reimbursement.db;

import java.awt.image.BufferedImage;
import com.revature.team201.reimbursement.users.User;

public interface UserDAO {
	public User login(String username, String password);
	public BufferedImage getReimbursementImage(Integer reimbursementId);
	public void addUser(User user, String password);
	public boolean checkForUserName(String username);
}
