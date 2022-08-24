package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAll ();
    User get(Long id);
    void delete(Long id);
    void update(User updatedUser);
    List<User> search(String keyword);

    User findByUsername(String username);

    List<Role> listRoles();
}
