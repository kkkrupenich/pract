package com.example.spring.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Role;
import com.example.spring.services.RoleService;

@RestController
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("roles")
	public List<Role> getRoles() {
		return roleService.getRoles();
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("addrole")
	public Role addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("updaterole/{id}")
	public Role updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
		return roleService.updateRole(id, role);
	}

	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("deleterole/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
		return ResponseEntity.ok("Todo deleted successfully!.");
	}
}
