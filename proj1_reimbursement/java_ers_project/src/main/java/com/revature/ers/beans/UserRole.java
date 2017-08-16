package com.revature.ers.beans;

import java.util.*;

public enum UserRole {
	EMPLYEE(1),MANAGER(2);
	
	private static Map<Integer, UserRole> map = new HashMap<Integer, UserRole>();

    static {
        for (UserRole role : UserRole.values()) {
            map.put(role.id, role);
        }
    }
	
	private final int id;
	private UserRole(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static UserRole valueOf(int id) {
        return map.get(id);
    }
}
