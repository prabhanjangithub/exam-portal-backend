package com.exam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
//Long :- id / class:- Users
  //  public Users findByUsername(String username);
      Optional<Users> findByUsername(String username);
    
    
}
