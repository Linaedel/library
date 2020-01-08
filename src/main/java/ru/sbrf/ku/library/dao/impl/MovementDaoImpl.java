package ru.sbrf.ku.library.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.ku.library.dao.MovementDao;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.entities.Movement;

import javax.persistence.EntityManager;

@Repository
public class MovementDaoImpl implements MovementDao {
    private final EntityManager em;

    @Autowired
    public MovementDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void add(LibraryEntity entity) {
        if (entity instanceof Movement) {
            em.persist(entity);
        }
    }
}