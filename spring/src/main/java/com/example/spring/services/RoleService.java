package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Role;
import com.example.spring.repositories.RoleRepository;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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