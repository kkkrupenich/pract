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

import com.example.spring.entities.Role;
import com.example.spring.services.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("roles")
	public List<Role> getRoles() {
		return roleService.getRoles();
	}

	@PostMapping("addrole")
	public Role addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}

	@DeleteMapping("deleterole/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
		return ResponseEntity.ok("Todo deleted successfully!.");
	}
}
