package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.PersonDao;
import ru.sbrf.ku.library.dao.UserRole;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.services.PersonService;

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
    public Set<Long> getHoldersId() {
        return personDao.list().stream().filter(person -> person.getType().equals(1)).map(p -> p.getId()).collect(Collectors.toSet());
    }

    @Override
    public Person getPerson(Long id) {
        return personDao.get(id);
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
    public void addOrUpdate(Person person){
        if (person.getId() == null) {
            addNewReader(person);
        } else {
            person.setRoles(personDao.get(person.getId()).getRoles());
            updatePerson(person);
        }
    }

    @Override
    public void addNewReader(Person person) {
        person.addRole(personDao.get(UserRole.READER));
        personDao.add(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.update(person);
    }

    @Override
    public void removePerson(Long id) {
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
