package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;

public class AdressDao implements IAdressDao {
    EntityManager entityManager;
    public AdressDao() {
        entityManager = HibernateUtil.getEntityManger();
    }

    @Override
    public List<Adresse> getAll() {
        return entityManager.createQuery("from Adresse ", Adresse.class).getResultList();
    }
    @Override
    public void add(Adresse e1) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(e1);
            entityManager.merge(e1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public Adresse getById(int id) {
        return entityManager.find(Adresse.class, id);
    }

}
