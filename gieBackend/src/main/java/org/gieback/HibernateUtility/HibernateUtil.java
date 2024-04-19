package org.gieback.HibernateUtility;

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
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_SC");
                entityManager = factory.createEntityManager();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return entityManager;
    }
}