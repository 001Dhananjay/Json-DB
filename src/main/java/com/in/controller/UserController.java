package com.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.DTO.UserResponse;
import com.in.Entity.User;
import com.in.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;



    // Save a single user
    @PostMapping
    public User createUser(@RequestBody User user) {
        try {
            String addressJson = objectMapper.writeValueAsString(user.getAddressMap());
            user.setAddress(addressJson);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize address", e);
        }
    }

    // Save multiple users
    @PostMapping("/batch")
    public List<User> createUsers(@RequestBody List<User> users) {
        try {
            for (User user : users) {
                String addressJson = objectMapper.writeValueAsString(user.getAddressMap());
                user.setAddress(addressJson);
            }
            return userRepository.saveAll(users);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize address fields", e);
        }
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());

        try {
            Map<String, Object> addressMap = objectMapper.readValue(user.getAddress(), Map.class);
            response.setAddress(addressMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse address", e);
        }

        return response;
    }

    // Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            try {
                String addressJson = objectMapper.writeValueAsString(updatedUser.getAddressMap());
                user.setAddress(addressJson);
            } catch (Exception e) {
                throw new RuntimeException("Failed to serialize updated address", e);
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    // All recode
    @GetMapping
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setName(user.getName());

            try {
                Map<String, Object> addressMap = objectMapper.readValue(user.getAddress(), Map.class);
                response.setAddress(addressMap);
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse address for user " + user.getId(), e);
            }

            responses.add(response);
        }

        return responses;
    }



}
