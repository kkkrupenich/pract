package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public User updateUser(Long id, String password) throws NotFoundException {
        Optional<User> existedUser = userRepository.findById(id);
        if (existedUser.isEmpty())
            throw new NotFoundException();
        User user = existedUser.get();
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User addUser(RegisterModel registerModel) throws NotFoundException {
        Optional<Role> existedRole = roleRepository.findByName("User");
        if (existedRole.isEmpty()) {
            throw new NotFoundException();
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

        return userRepository.save(user);
    }

    public User getUserById(Long id) throws NotFoundException {
        Optional<User> existedUser = userRepository.findById(id);
        if (existedUser.isEmpty())
            throw new NotFoundException();
        return existedUser.get();
    }

    public User getUserByEmail(String email) throws NotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new NotFoundException();
        return user.get();
    }

    public User getUserByEmailAndPassword(String email, String password) throws NotFoundException {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty())
            throw new NotFoundException();
        return user.get();
    }

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
