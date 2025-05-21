package com.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.DTO.UserResponse;
import com.in.Entity.User;
import com.in.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());

        // Parse JSON string to map with @JsonAnySetter
        objectMapper.readerForUpdating(response).readValue(user.getAddress());

        return response;
    }
}
