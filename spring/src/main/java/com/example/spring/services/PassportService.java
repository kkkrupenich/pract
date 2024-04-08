package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Passport;
import com.example.spring.repositories.PassportRepository;

@Service
public class PassportService {

    PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public List<Passport> getPassports() {
        return passportRepository.findAll();
    }

    public Passport getPassportById(Long id) {
        return passportRepository.findById(id).get();
    }

    public Passport addPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    public ResponseEntity<String> deletePasspors(Long id) {
        passportRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
