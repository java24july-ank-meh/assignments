package com.revature.main;

import com.revature.dao.*;
import com.revature.domain.Cave;

public class Main {
	public static void main(String[] args) {
		CaveDao dao = new CaveDaoImpl();
		Cave c = new Cave();
		c.setName("Underwater Fortress");
		c.setMaxBears(38);
		
		//dao.readCave(1);
		//dao.createCave(c);
		//System.out.print(dao.readAllCaves());
		dao.feedBear(1, 100);
	}
}
