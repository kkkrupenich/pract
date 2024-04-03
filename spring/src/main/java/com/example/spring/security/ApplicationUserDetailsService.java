package com.example.spring.security;

import com.example.spring.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var user = userRepository.findById(Long.parseLong(id));

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No such user with id " + id);
        }

        var roleName = user.get().getRole().getName();

        return new User(
                user.get().getId().toString(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));
    }
}