package com.example.demo.service;


import com.example.demo.model.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    void saveUser(String username, String password, String address, String phone);

    Optional<User> getUserById(Long id);

    User getUserByUsername(String name);

    void updateUser(User user);

}
