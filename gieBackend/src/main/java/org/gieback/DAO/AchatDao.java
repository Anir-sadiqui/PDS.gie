package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.gieback.Entity.Achat;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Contact;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
            // Persist the Achat, which will also persist the details due to CascadeType.ALL
            entityManager.persist(achat);
            transaction.commit();
            System.out.println("Commited");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
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
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(String id, Map<String, Integer> attributs) {
        entityManager.getTransaction().begin();
        Achat p = entityManager.find(Achat.class, id);
        if (p != null) {
            for (Map.Entry<String, Integer> entry : attributs.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                switch (key) {
                    case "Quantite":
                        // Assuming only one detail for simplicity
                        AchatDetail detail = p.getDetails().get(0);
                        detail.setQuantity(value);
                        detail.setTotalPrice(value * detail.getProduct().getPrix());
                        break;
                    case "Fournisseur":
                        p.getSupplier().setId((long) value);
                        break;
                }
            }
            entityManager.merge(p);
        } else {
            System.out.println("id incorrect");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Achat> chercherParDate(Date date) {
        return entityManager.createQuery("SELECT a FROM Achat a WHERE a.purchaseDate = :date", Achat.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Achat> chercherParId(int id) {
        return entityManager.createQuery("SELECT a FROM Achat a WHERE a.id = :id", Achat.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Achat> chercherParFournisseur(Contact f) {
        return entityManager.createQuery("SELECT a FROM Achat a WHERE a.supplier = :f", Achat.class)
                .setParameter("f", f)
                .getResultList();
    }
}
