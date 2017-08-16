package com.revature.ers.beans;

import java.util.HashMap;
import java.util.Map;

public enum RequestType {
	MEDICAL(1), TRAVEL(2), RELOCATION(3), COMMUTING(4), TRAINING(5), SUPPLIES(6);
	
	private static Map<Integer, RequestType> map = new HashMap<Integer, RequestType>();

    static {
        for (RequestType role : RequestType.values()) {
            map.put(role.id, role);
        }
    }
    
    public static RequestType valueOf(int id) {
        return map.get(id);
    }
	
	private final int id;
	private RequestType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
