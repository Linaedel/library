package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.PlacementDao;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Placement;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class PlacementDaoImpl extends AbstractDaoImpl implements PlacementDao {
    @Override
    public List<Placement> list() throws SQLException {
        TypedQuery<Placement> query = em.createQuery("select p from Placement p",Placement.class);
        return query.getResultList();    }

    @Override
    public void add(LibraryEntity entity) {
        if (entity instanceof Placement) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }
}
