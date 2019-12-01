package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.ClientDao;
import ru.sbrf.ku.library.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDaoImpl extends AbstractDaoImpl implements ClientDao {

    @Override
    public void insert( Client client ) {
        em.getTransaction().begin();
        em.persist(client);
        System.out.println( ">>>>>>>>>>> created:" + client );
        System.out.println( ">>>>>>>>>>> before commit" );

        em.getTransaction().commit();
    }

    @Override
    public List<Client> list() {
        TypedQuery<Client> query = em.createQuery("select u from Client u",Client.class);
        return query.getResultList();
    }

}
