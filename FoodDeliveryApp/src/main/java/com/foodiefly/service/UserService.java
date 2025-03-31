package com.foodiefly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodiefly.entity.User;
import com.foodiefly.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository ur; // Consider renaming to userRepository for clarity

    public List<User> getAllUser() {
        return ur.findAll(); // findAll() already returns List<User>, no cast needed
    }

    public User getById(int userId) {
        return ur.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
    }

    public String insertUser(User user) {
        User savedUser = ur.save(user);
        return "User Details are Inserted";
    }

    public String updateUser(int userId, User user) {
        Optional<User> existingUser = ur.findById(userId);
        if (existingUser.isPresent()) {
            user.setUserId(userId); // Ensure the ID matches the existing user
            ur.save(user);
            return "User Details are Updated";
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    public String deleteUser(int userId) {
        Optional<User> user = ur.findById(userId);
        if (user.isPresent()) {
            ur.delete(user.get());
            return "User Details are deleted";
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    // New method to find user by username
    public User findByUsername(String username) {
        return ur.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }
}