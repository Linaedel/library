package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.entities.LibraryEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {
    private final EntityManager em;

    @Autowired
    public ClientDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Client> list() {
        TypedQuery<Client> query = em.createQuery("select u from Client u",Client.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void add(LibraryEntity entity) {
        if (entity instanceof Client) {
            em.persist(entity);
        }
    }
}
