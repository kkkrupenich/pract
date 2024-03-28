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

import com.example.spring.entities.Chance;
import com.example.spring.services.ChanceService;

@RestController
public class ChanceController {

    @Autowired
    ChanceService chanceService;

    @GetMapping("chances")
    public List<Chance> getRoles() {
        return chanceService.getRoles();
    }

    @PostMapping("addchance")
    public Chance addRole(@RequestBody Chance role) {
        return chanceService.saveRole(role);
    }

    @DeleteMapping("deletechance/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        chanceService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
