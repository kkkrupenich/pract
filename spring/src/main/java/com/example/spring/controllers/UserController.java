package com.example.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.User;
import com.example.spring.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public List<User> getRoles() {
        return userService.getRoles();
    }

    @PostMapping("adduser")
    public User addRole(@RequestBody User role) {
        return userService.saveRole(role);
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        userService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
