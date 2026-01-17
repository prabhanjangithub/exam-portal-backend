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

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("User already exists");
        }

        // ✅ Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Attach managed Role entity from DB
        Set<UserRole> finalRoles = new java.util.HashSet<>();

        for (UserRole ur : userRoles) {

            String roleName = ur.getRole().getRoleName();

            var roleFromDb = roleRepository
                    .findByRoleName(roleName)
                    .orElseThrow(() ->
                        new RuntimeException("Role not found: " + roleName)
                    );

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(roleFromDb);

            finalRoles.add(userRole);
        }

        user.setUserRoles(finalRoles);

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
