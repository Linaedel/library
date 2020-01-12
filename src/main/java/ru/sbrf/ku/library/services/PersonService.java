package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Person;

import java.util.List;

public interface PersonService {
    Person findByUsername(String username);
    List<Person> getLibrarianList();
    List<Person> getClientList();
    void addNewLibrarian(String username, String password);
    void addNewClient(Person person);
    void updateClient(Person person);
    void removeUser(Long id);
}