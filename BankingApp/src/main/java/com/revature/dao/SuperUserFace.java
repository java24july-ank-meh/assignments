package com.revature.dao;

import com.revature.domain.Clients;

import java.util.List;

public interface SuperUserFace {
	
	public void addClient();
	public void viewClient(int id);
	public List<Clients> viewAllClients();
	public void updateClient(int c);
	public void deleteClient(int id);
	public void deleteAllClients();

}
