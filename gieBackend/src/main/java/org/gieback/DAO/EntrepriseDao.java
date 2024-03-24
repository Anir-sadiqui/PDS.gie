package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;

public class EntrepriseDao implements IEntrepriseDao {
    EntityManager entityManager;
    public EntrepriseDao() {
        entityManager = HibernateUtil.getEntityManger();
    }
    @Override
    public List<Entreprise> getAll() {
        return entityManager.createQuery("from Entreprise ", Entreprise.class).getResultList();
    }
    @Override
    public void add(Entreprise e1) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            System.out.println(e1.getAdresse());
            if (e1.getAdresse() != null ) {
                entityManager.persist(e1.getAdresse());
                e1.setAdresse(e1.getAdresse());
            }
            entityManager.persist(e1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Entreprise getById(int id) {
        return entityManager.find(Entreprise.class, id);
    }

}
