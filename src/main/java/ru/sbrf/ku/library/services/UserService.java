package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> getLibrarianList();
    void addNewLibrarian(String username, String Password);
    void removeUser(Long id);
}