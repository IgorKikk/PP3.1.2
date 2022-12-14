package com.example.PP312.dao;

import com.example.PP312.model.Role;
import com.example.PP312.model.User;
import com.example.PP312.service.RoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext()
    private EntityManager entityManager;

    private RoleService roleService;

    public UserDaoImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void createUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        if(user.getRoles() != null) {
            Iterator<Role> iterator = user.getRoles().iterator();
            while (iterator.hasNext()) {
                Role role = roleService.finedRoleByRoleName(iterator.next().getRoleName());
                roleSet.add(role);
            }
        }
        user.setRoles(roleSet);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        Iterator<Role> iterator = user.getRoles().iterator();
        while (iterator.hasNext()) {
            Role role = roleService.finedRoleByRoleName(iterator.next().getRoleName());
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select distinct u from User u join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username")
                                   .setParameter("username", username);
        User user = (User) query.getSingleResult();
        if(user == null) {
             throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        user.getRoles().size();
        return user;
    }
}
