package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}