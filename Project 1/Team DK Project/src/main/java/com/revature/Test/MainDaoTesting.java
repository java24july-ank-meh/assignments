package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Photo;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class MainDaoTesting {

	public static void main(String[] args) {
		ReimbursementDaoImpl rD = new ReimbursementDaoImpl();
        Reimbursement i = rD.readmyReimburse();
//        System.out.println(i.toString());
//        String json = new Gson().toJson(i);
//        System.out.println(json);

	}
}
