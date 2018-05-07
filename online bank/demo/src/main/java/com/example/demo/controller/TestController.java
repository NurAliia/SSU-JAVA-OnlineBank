package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by NurAliya on 07.05.2018.
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository service;

    private final RoleRepository roles;

    @GetMapping("/info")
    public List<User> info() {
        return (List<User>) service.findAll();
    }

    @GetMapping("/roles")
    public List<Role> roles() {
        return (List<Role>) roles.findAll();
    }
}
