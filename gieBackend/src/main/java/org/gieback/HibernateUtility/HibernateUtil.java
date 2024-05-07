package org.gieback.HibernateUtility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("PU_SC");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManger() {
        return getEntityManager();
    }
}

