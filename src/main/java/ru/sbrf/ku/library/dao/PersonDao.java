package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.entities.Role;

import java.util.List;

public interface PersonDao extends AbstractDao{
    Person findByUsername(String username);
    List<Person> list();
    List<Person> fullList();
    void update(Person person);
    void remove(Long id);
    Person get(Long id);
    Role get(UserRole userRole);
}
