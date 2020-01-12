package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.PersonDao;
import ru.sbrf.ku.library.dao.UserRole;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.entities.Role;
import ru.sbrf.ku.library.services.PersonService;
import ru.sbrf.ku.library.viewmodel.ClienInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findByUsername(String username) {
        return personDao.findByUsername(username);
    }

    @Override
    public List<Person> getLibrarianList() {
        return getList("ROLE_LIBRARIAN");
    }

    @Override
    public List<Person> getClientList() {
        return getList("ROLE_READER");
    }

    @Override
    public void addNewLibrarian(String username, String password) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        person.setDeleted(0);
        person.addRole(personDao.get(UserRole.LIBRARIAN));
        personDao.add(person);
    }

    @Override
    public void addNewClient(Person person) {
        person.addRole(personDao.get(UserRole.READER));
        personDao.add(person);
    }



    @Override
    public void updateClient(Person person) {

    }

    @Override
    public void removeUser(Long id) {
        personDao.remove(id);
    }

    private List<Person> getList(String role){
        return personDao.list().stream().filter(
                user -> user.getRoles().stream().anyMatch(
                        r -> r.getName().equals(role)
                )
        ).collect(Collectors.toList());
    }
}
