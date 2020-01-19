package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Person;

import java.util.List;
import java.util.Set;

public interface PersonService {
    Person findByUsername(String username);
    List<Person> getLibrarianList();
    List<Person> getClientList();
    Set<Long> getHoldersId();
    Person getPerson(Long id);
    void addOrUpdate(Person person);
    void updatePerson(Person person);
    void removePerson(Long id);
    void addNewLibrarian(String username, String password);
    void addNewReader(Person person);

}