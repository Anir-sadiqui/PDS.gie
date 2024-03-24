package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.gieback.Entity.Contact;
import org.gieback.Entity.Personne;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;

public   class PersonneDao implements IPersonneDao{

    EntityManager entityManager;

    public PersonneDao() {
        entityManager = HibernateUtil.getEntityManger();
    }



    @Override
    public List<Personne> getAll() {
        return entityManager.createQuery("from Personne ", Personne.class).getResultList();
    }

    @Override
    public void add(Personne p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(p);
           entityManager.merge(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public Personne getById(int id) {
        return entityManager.find(Personne.class, id);
    }


}

