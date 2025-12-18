package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.model.Role;
import com.exam.model.UserRole;
import com.exam.model.Users;
import com.exam.servise.UserServise;
@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner { 

	@Autowired
	private UserServise userServise;
 
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");

		// Users users=new Users();
		// users.setFirstName("jay");
		// users.setLastName("bhairavi");
		// users.setUsername("root");
		// users.setPassword("root");
		// users.setEmail("abc@gmail.com");
		// users.setProfile("aaa.png");

		// Role role1=new Role();
		// role1.setRoleId((long) 44);
		// role1.setRoleName("admin");

		// Set<UserRole> userRoleSet=new HashSet<>();
		// UserRole userRole= new UserRole();
		// userRole.setRole(role1);
		// userRole.setUser(users);
		// userRoleSet.add(userRole);
		// Users user1= this.userServise.createUser(users, userRoleSet);
		// System.out.println(user1.getUsername());


	}

}
