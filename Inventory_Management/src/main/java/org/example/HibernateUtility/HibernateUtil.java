package org.example.HibernateUtility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;


public class HibernateUtil {

    @PersistenceContext
    private static EntityManager entityManager;
    public static EntityManager getEntityManger() {
        if (entityManager == null) {
            try {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("manager1");
                entityManager = factory.createEntityManager();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return entityManager;
    }
}