package com.example.PP312.dao;

import com.example.PP312.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public void createRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role finedRoleByRoleName(String roleName) {
        Query query = entityManager.createQuery("select r from Role r where r.roleName = :roleName")
                .setParameter("roleName", roleName);
        return  (Role) query.getSingleResult();
    }

    @Override
    public List<Role> getAllRolesOfUser(long id) {
        return entityManager.createQuery("select r from Role r join r.users u where u.id = :id")
                .setParameter("id", id).getResultList();
    }

}
