package com.revature.ers.beans;

import java.util.HashMap;
import java.util.Map;

public enum RequestStatus {
	PENDING(1),DENIED(2),APPROVED(3);
	
	private final int id;
	private RequestStatus(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	private static Map<Integer, RequestStatus> map = new HashMap<Integer, RequestStatus>();

    static {
        for (RequestStatus role : RequestStatus.values()) {
            map.put(role.id, role);
        }
    }
    
    public static RequestStatus valueOf(int id) {
        return map.get(id);
    }
}
