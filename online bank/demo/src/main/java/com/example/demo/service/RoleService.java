package com.example.demo.service;


import com.example.demo.model.Role;

public interface RoleService {

    Role getRoleById(Long id);

    Role getRoleByRolename(String name);

}
