package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository =  roleRepository;
    }


    @Override
    public void saveUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByRole("USER"));
        user.setRoles(roleSet);
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Override
    public void saveUser(String username, String password, String address, String phone) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByRole("USER"));
        user.setRoles(roleSet);
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void updateUser(User user) {
       userRepository.save(user);
    }
}
