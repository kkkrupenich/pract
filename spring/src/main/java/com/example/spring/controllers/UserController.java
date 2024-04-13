package com.example.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.User;
import com.example.spring.models.LoginModel;
import com.example.spring.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping("updateuser")
    public User updateUser(@RequestBody LoginModel loginModel, Principal principal) throws NotFoundException {
        return userService.updateUser(Long.parseLong(principal.getName()), loginModel.getPassword());
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
