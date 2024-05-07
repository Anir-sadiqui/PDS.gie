package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Contact;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.Date;
import java.util.List;

public class AchatDao implements IAchatDao {

    private EntityManager entityManager;

    public AchatDao() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public List<Achat> getAll() {
        return entityManager.createQuery("SELECT a FROM Achat a", Achat.class).getResultList();
    }

    @Override
    public void add(Achat achat) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(achat);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Achat achat = entityManager.find(Achat.class, id);
            if (achat != null) {
                entityManager.remove(achat);
                entityManager.flush();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(int id, Date newDate, Contact newFournisseur) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Achat achat = entityManager.find(Achat.class, id);
            if (achat != null) {
                achat.setPurchaseDate(newDate);
                achat.setSupplier(newFournisseur);
                entityManager.merge(achat);
                entityManager.flush();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Achat> chercherParDate(Date date) {
        Query query = entityManager.createQuery("SELECT a FROM Achat a WHERE a.purchaseDate = :date", Achat.class);
        ((TypedQuery<?>) query).setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Achat> chercherParId(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Achat a WHERE a.id = :id", Achat.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Achat> chercherParFournisseur(Contact f) {
        Query query = entityManager.createQuery("SELECT a FROM Achat a WHERE a.supplier = :f", Achat.class);
        query.setParameter("f", f);
        return query.getResultList();
    }
}

