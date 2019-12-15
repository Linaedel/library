package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.MovementDao;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Movement;

public class MovementDaoImpl extends AbstractDaoImpl implements MovementDao {

    @Override
    public void add(LibraryEntity entity) {
        if (entity instanceof Movement) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }
}