package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.dao.AbstractDao;
import ru.sbrf.ku.library.entities.LibraryEntity;
import ru.sbrf.ku.library.utils.EntityManagerContext;

import javax.persistence.EntityManager;

public abstract class AbstractDaoImpl implements AbstractDao {
    protected EntityManager em = EntityManagerContext.getInstance();

    public abstract void add(LibraryEntity entity);
}
