package ru.sbrf.ku.library.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerContext {
    private static EntityManager em;

    public static EntityManager getInstance(){
        if (em == null) {
            synchronized (EntityManagerContext.class){
                if (em == null){
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
                    em = emf.createEntityManager();
                }
            }
        }
        return em;
    }
}
