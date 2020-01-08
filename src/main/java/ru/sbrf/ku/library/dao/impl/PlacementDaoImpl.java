package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.PlacementDao;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Placement;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlacementDaoImpl implements PlacementDao {
    private final EntityManager em;

    @Autowired
    public PlacementDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Placement> list() throws SQLException {
        TypedQuery<Placement> query = em.createQuery("select p from Placement p", Placement.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void add(LibraryEntity entity) {
        if (entity instanceof Placement) {
            em.persist(entity);
        }
    }
}
