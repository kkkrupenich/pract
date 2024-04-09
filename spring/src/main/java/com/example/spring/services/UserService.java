package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.User;
import com.example.spring.models.RegisterModel;
import com.example.spring.entities.Passport;
import com.example.spring.entities.Role;
import com.example.spring.repositories.PassportRepository;
import com.example.spring.repositories.RoleRepository;
import com.example.spring.repositories.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PassportRepository passportRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            PassportRepository passportRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passportRepository = passportRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, String password) {
        Optional<User> existedUser = userRepository.findById(id);
        if (existedUser.isPresent()) {
            User user = existedUser.get();
            user.setPassword(password);
            return userRepository.save(user);
        }
        return null;
    }

    public User addUser(RegisterModel registerModel) {
        Optional<Role> existedRole = roleRepository.findByName("User");
        if (existedRole.isEmpty()) {
            return null;
        }

        Passport passport = new Passport();
        passport.setSerialNumber(registerModel.getSerialNumber());
        passport.setIdentificationNumber(registerModel.getIdentificationNumber());
        passport.setRegistration(registerModel.getRegistration());
        passport.setIssueDate(registerModel.getIssueDate());
        passport.setExpirationDate(registerModel.getExpirationDate());
        passportRepository.save(passport);

        User user = new User();
        user.setEmail(registerModel.getEmail());
        user.setPassword(registerModel.getPassword());
        user.setFio(registerModel.getFio());
        user.setRole(existedRole.get());
        user.setPassport(passport);
        user.setBalance(0);
        user.setPassport(passport);

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> existedUser = userRepository.findById(id);
        
        return existedUser.isPresent() ? existedUser.get() : null;
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User getUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
