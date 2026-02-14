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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiseImpl implements UserServise {
    private static final Logger logger = LoggerFactory.getLogger(UserServiseImpl.class);


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users createUser(Users user, Set<UserRole> userRoles) throws Exception {

        if (user == null) {
            logger.error("Attempted to create null user");
            throw new IllegalArgumentException("User cannot be null");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.warn("User already exists with username: {}", user.getUsername());

            throw new Exception("User already exists");
        }

        // ✅ Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Attach managed Role entity from DB
        Set<UserRole> finalRoles = new java.util.HashSet<>();

        for (UserRole ur : userRoles) {

            String roleName = ur.getRole().getRoleName();
            logger.info("Assigning role '{}' to user '{}'", roleName, user.getUsername());


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
        logger.info("User created successfully with ID: {}", user.getId());

        return userRepository.save(user);
    }

   @Override
    public Users getUsers(String username) {

        logger.info("Fetching user with username: {}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("User not found with username: {}", username);
                    return new RuntimeException("User not found");
                });
    }

    @Override
    public void detetUser(Long userId) {

        logger.warn("Deleting user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            logger.error("User not found for deletion with ID: {}", userId);
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(userId);

        logger.info("User deleted successfully with ID: {}", userId);
    }
}
