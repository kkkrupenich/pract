package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.User;
import com.example.spring.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<User> getRoles() {
        return userRepository.findAll();
    }

    public User saveRole(User role) {
        return userRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
