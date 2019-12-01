package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.entities.Client;
import ru.sbrf.ku.library.entities.LibraryEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDaoImpl extends AbstractDaoImpl implements ClientDao {

    @Override
    public List<Client> list() {
        TypedQuery<Client> query = em.createQuery("select u from Client u",Client.class);
        return query.getResultList();
    }

    @Override
    public void add(LibraryEntity entity) {
        if (entity instanceof Client) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }
}
