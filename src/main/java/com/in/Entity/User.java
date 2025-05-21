package com.in.Entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String name;

    @Column(columnDefinition = "json")
    private String address; // Stored as JSON in DB

    @Transient
    private Map<String, Object> dynamicFields = new HashMap<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Object> getDynamicFields() {
        return dynamicFields;
    }

    @JsonAnySetter
    public void setDynamicField(String key, Object value) {
        dynamicFields.put(key, value);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}