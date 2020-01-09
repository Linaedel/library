package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.UserDao;
import ru.sbrf.ku.library.dao.UserRole;
import ru.sbrf.ku.library.entities.User;
import ru.sbrf.ku.library.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getLibrarianList() {
        return userDao.list().stream().filter(
                user -> user.getRoles().stream().anyMatch(
                        role -> role.getName().equals("ROLE_LIBRARIAN")
                )
        ).collect(Collectors.toList());
    }

    @Override
    public void addNewLibrarian(String username, String password) {
        userDao.add(username, password, UserRole.LIBRARIAN);
    }

    @Override
    public void removeUser(Long id) {
        userDao.remove(id);
    }


}
