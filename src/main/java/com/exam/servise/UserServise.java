package com.exam.servise;

import java.util.Set;

import com.exam.model.UserRole;
import com.exam.model.Users;
//first here method creaton happen 
public interface UserServise {
    
    //creating user
    public Users createUser(Users user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public Users getUsers(String username);

    //delete user by id 
    public void detetUser(Long userId);
    
}
