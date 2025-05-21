package com.in.DTO;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class UserResponse {

    private String id;
    private String name;

    private Map<String, Object> address = new HashMap<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonAnySetter
    public void setAddress(String key, Object value) {
        address.put(key, value);
    }
}