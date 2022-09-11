package com.example.PP312.dao;

import com.example.PP312.model.Role;

import java.util.List;

public interface RoleDao {

    void createRole(Role role);

    List<Role> getAllRoles();

    Role finedRoleByRoleName(String roleName);

    List<Role> getAllRolesOfUser(long id);
}
