package com.exam.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.UserRole;
import com.exam.model.Users;
import com.exam.servise.UserServise;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    
    @Autowired 
    private UserServise userServise; //Tells Spring: “Give me an object of UserServise so I can use its methods.”
   
    
   
     //creatng user
    @PostMapping("/")
    public Users createUser(@RequestBody Users user) throws Exception{
        logger.info("Received request to create user with username: {}", user.getUsername());

        Set<UserRole> roles=new HashSet<>();//A set to hold roles assigned to this user.
    Role role= new Role();
    if("ADMIN".equalsIgnoreCase(user.getProfile())){
        logger.info("Assigning ADMIN role to user: {}", user.getUsername());

        role.setRoleId(44L);
        role.setRoleName("ROLE_ADMIN");
    }
    else{
        logger.info("Assigning NORMAL role to user: {}", user.getUsername());

        role.setRoleId(45L);
        role.setRoleName("ROLE_NORMAL");
    }
    


    UserRole userRole=new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);//Links the User and Role together using a bridge object UserRole.
    roles.add(userRole);

    //roles.add(new UserRole());
    logger.info("User created successfully with ID: {}", user.getId());

    return this.userServise.createUser(user, roles);
    }

    @GetMapping("/{username}")
    
    public Users getUsers(@PathVariable("username") String username)
    {
        logger.info("Fetching user with username: {}", username);
        return this.userServise.getUsers(username);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId)
    {
       this.userServise.detetUser(userId);
       logger.info("User deleted successfully with ID: {}", userId);
    }

     @GetMapping("/current-user")
    public Users getCurrentUser(Principal principal) {
        logger.info("Fetching current logged-in user: {}", principal.getName());
        return userServise.getUsers(principal.getName());
    }
    
}
