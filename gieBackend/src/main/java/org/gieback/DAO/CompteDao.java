package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.gieback.Entity.Compte;
import org.gieback.Entity.Entreprise;
import org.gieback.HibernateUtility.HibernateUtil;

public class CompteDao implements ICompteDao  {
    EntityManager entityManager;

    public CompteDao() {
        entityManager = HibernateUtil.getEntityManger();
    }

    @Override
    public void add(Compte c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(c);
            entityManager.merge(c);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Boolean verifyMdp(String mdp, String email) {
      Compte c = getByEmail(email);
      if (c.getMDP() == mdp){
          return true;
      }
      return false;
    }

    @Override
    public Compte getByEmail(String email) {
        return entityManager.find(Compte.class, email);
    }
}




