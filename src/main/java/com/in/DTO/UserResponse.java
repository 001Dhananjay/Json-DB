package com.in.DTO;

import java.util.Map;

public class UserResponse {
    private String id;
    private String name;
    private Map<String, Object> address;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map<String, Object> getAddress() { return address; }
    public void setAddress(Map<String, Object> address) { this.address = address; }
}