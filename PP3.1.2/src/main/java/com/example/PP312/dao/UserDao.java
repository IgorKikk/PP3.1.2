package com.example.PP312.dao;


import com.example.PP312.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDao extends UserDetailsService {

    void createUser(User user);

    void deleteUser(long id);

    void updateUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

    User finedUserByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
