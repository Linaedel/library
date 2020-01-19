package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.PersonDao;
import ru.sbrf.ku.library.dao.UserRole;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    private final EntityManager em;

    @Autowired
    public PersonDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Person findByUsername(String username) {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.username = ?1", Person.class);
        List<Person> result = query.setParameter(1, username).getResultList();
        return result.size() > 0 ? result.get(0) : null;    }

    @Override
    public List<Person> list() {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.deleted = 0", Person.class);
        return query.getResultList();
    }

    @Override
    public List<Person> fullList() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Person person) {
        em.merge(person);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Person person = em.find(Person.class, id);
        if (person != null) {
            person.setDeleted(1);
            em.merge(person);
        }
    }

    @Override
    public Person get(Long id) {
        Person person = em.find(Person.class, id);
        return person.getDeleted() == 1 ? null : person;
    }

    @Override
    public Role get(UserRole userRole) {
        return em.find(Role.class,(long) userRole.ordinal());
    }

    @Override
    @Transactional
    public void add(LibraryEntity entity) {
        if (entity instanceof Person) {
            Person presentPerson = findByUsername(((Person) entity).getUsername());
            if (presentPerson != null) {
                presentPerson.setPassword(((Person) entity).getPassword());
                presentPerson.setDeleted(0);
                em.merge(presentPerson);
            } else {
                em.persist(entity);
            }
        }
    }
}
