package com.user.usermicroservice.service;


import com.user.usermicroservice.entity.User;


import java.util.List;

public interface UserService {
    User registerUser(User user);
    User updateUser(Long id, User user);
    User findByUsername(String username);
    List<User> getAllUsers();
}
