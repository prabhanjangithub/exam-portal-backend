package com.exam.servise.impl;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.model.UserRole;
import com.exam.model.Users;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.servise.UserServise;

@Service
public class UserServiseImpl implements UserServise {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users createUser(Users user, Set<UserRole> userRoles) throws Exception {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Users local = userRepository
                .findByUsername(user.getUsername())
                .orElse(null);

        if (local != null) {
            throw new Exception("User already exists");
        }

        // 🔴 ENCODE PASSWORD (MANDATORY)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save roles first
        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        // Assign roles
        user.getUserRoles().addAll(userRoles);

        // Save user
        return userRepository.save(user);
    }

    @Override
    public Users getUsers(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void detetUser(Long userId) {
        userRepository.deleteById(userId);
    }

    
}
