package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PassportRepository passportRepository;

    public UserService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(RegisterModel registerModel) {
        Role role = roleRepository.findByName("User").get();
        Passport passport = passportRepository.findById(registerModel.getPassportId()).get();
        User user = new User();
        user.setEmail(registerModel.getEmail());
        user.setPassword(registerModel.getPassword());
        user.setFio(registerModel.getFio());
        user.setRole(role);
        user.setPassport(passport);
        user.setBalance(0);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
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
