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

import com.example.spring.entities.Passport;
import com.example.spring.services.PassportService;

@RestController
public class PassportController {
    
    @Autowired
    PassportService passportService;

    @GetMapping("passports")
    public List<Passport> getPassports() {
        return passportService.getPassports();
    }

    @PostMapping("addpassport")
    public Passport addRole(@RequestBody Passport passport) {
        return passportService.savePassport(passport);
    }

    @DeleteMapping("deletepassport/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        passportService.deletePasspors(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
