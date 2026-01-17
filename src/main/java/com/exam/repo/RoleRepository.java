package com.exam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByRoleName(String roleName);
     
} 
