package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);
    List<User> list();
    void add(String username, String password, UserRole userRole);
    void remove(Long id);
}
