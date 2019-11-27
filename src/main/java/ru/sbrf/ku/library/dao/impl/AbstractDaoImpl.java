package ru.sbrf.ku.library.dao.impl;

import ru.sbrf.ku.library.utils.EntityManagerContext;

import javax.persistence.EntityManager;

public abstract class AbstractDaoImpl {
    protected EntityManager em = EntityManagerContext.getInstance();
}
