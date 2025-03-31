package com.foodiefly.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodiefly.entity.User;
import com.foodiefly.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return (List<User>) us.getAllUser();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable int id) {
        return us.getById(id);
    }

    @PostMapping("/user/insert")
    public String insertUser(@RequestBody User user) {
        System.out.println("its calling");
        return us.insertUser(user);
    }

    @PutMapping("/user/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        return us.updateUser(id, user);
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return us.deleteUser(id);
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        try {
            User user = us.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}