package com.example.PP312.service;

import com.example.PP312.model.Role;

import java.util.List;

public interface RoleService {

    void createRole(Role role);

    List<Role> getAllRoles();

    Role finedRoleByRoleName(String roleName);
}
