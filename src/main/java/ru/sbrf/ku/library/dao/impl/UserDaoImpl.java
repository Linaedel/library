package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.UserDao;
import ru.sbrf.ku.library.dao.UserRole;
import ru.sbrf.ku.library.entities.Role;
import ru.sbrf.ku.library.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = ?1", User.class);
        List<User> result = query.setParameter(1, username).getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public List<User> list() {
        return em.createQuery("select u from User u where u.deleted = 0", User.class).getResultList();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            user.setDeleted(1);
            em.merge(user);
        }
    }

    @Override
    @Transactional
    public void add(String username, String password, UserRole userRole) {
        User presentUser = findByUsername(username);
        if (presentUser != null) {
            presentUser.setPassword(password);
            presentUser.setDeleted(0);
            em.merge(presentUser);
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setDeleted(0);
            Set<Role> roles = new HashSet<>();
            roles.add(em.find(Role.class, (long) userRole.ordinal()));
            newUser.setRoles(roles);
            em.persist(newUser);
        }
    }
}
