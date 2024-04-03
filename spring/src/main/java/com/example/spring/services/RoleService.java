package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Role;
import com.example.spring.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}