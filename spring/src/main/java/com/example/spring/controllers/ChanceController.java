package com.example.spring.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    ChanceService chanceService;

    public ChanceController(ChanceService chanceService) {
        this.chanceService = chanceService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("chances")
    public List<Chance> getChance() {
        return chanceService.getChance();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("addchance")
    public Chance addChance(@RequestBody Chance chance) {
        return chanceService.addChance(chance);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("deletechance/{id}")
    public ResponseEntity<String> deleteChance(@PathVariable("id") Long id) {
        chanceService.deleteChance(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
