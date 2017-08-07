package com.revature.dao;

import java.util.List;
import com.revature.domain.Cave;

public interface CaveDao {
	public void createCave(Cave c);
	public void readCave(int id);
	public void updateCave(Cave c);
	public void deleteCave (int id);
	public List<Cave> readAllCaves();
	public void feedBear(int bid, int amt);
}