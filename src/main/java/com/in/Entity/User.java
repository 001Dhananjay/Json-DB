package com.in.Entity;

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
    private String address; // Stored in DB as JSON string

    @Transient
    private Map<String, Object> addressMap = new HashMap<>(); // Used for dynamic address input

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Map<String, Object> getAddressMap() { return addressMap; }
    public void setAddressMap(Map<String, Object> addressMap) { this.addressMap = addressMap; }
}
