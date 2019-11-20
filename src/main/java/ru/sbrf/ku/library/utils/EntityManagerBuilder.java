package ru.sbrf.ku.library.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerBuilder {

    public static EntityManager build(String className){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(className);
        return emf.createEntityManager();
    }
}

