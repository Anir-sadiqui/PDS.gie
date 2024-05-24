package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.gieback.Entity.*;
import org.gieback.HibernateUtility.HibernateUtil;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.PersonneService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AchatDao implements IAchatDao {

    private EntityManager entityManager;

    public AchatDao() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public List<Achat> getAll(Commande c) {
        Query query = entityManager.createQuery(" FROM Achat a WHERE a.c.id = : c", Achat.class);
        query.setParameter("c", c);
        return  query.getResultList();
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
    public void modifier(String id, Map<String, String> attributs) {
        entityManager.getTransaction().begin();
        Achat p = entityManager.find(Achat.class, id);
        if (p != null) {
            for (Map.Entry<String, String> entry : attributs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "Product":
                        p.getDetails().getProduct().setDescription(value);
                        break;
                    case "Quantite":
                        p.getDetails().setQuantity(Integer.parseInt(value));
                        p.getDetails().setTotalPrice(Integer.parseInt(value) * p.getDetails().getProduct().getPrix() );
                        break;
                    case "FournisseurE":
                        EntrepriseService es = new EntrepriseService();
                        p.setSupplier(es.getEnterpriseById(Integer.parseInt(value)));
                        break;
                    case "FournisseurP":
                        PersonneService ps = new PersonneService();
                        p.setSupplier(ps.getPersonneById(Integer.parseInt(value)));
                        break;

                }
            }
            entityManager.merge(p);
        }
        else { System.out.println("id incorrect");}
        entityManager.getTransaction().commit();


    }
    @Override
    public List<Achat> chercherParDate(LocalDate date) {
        Query query = entityManager.createQuery("FROM Achat a WHERE a.purchaseDate = :date", Achat.class);
        query.setParameter("date", date);
        List <Achat> a = query.getResultList();
        return a;
    }



    @Override
    public List<Achat> chercherParFournisseur(int idf) {
        Query query = entityManager.createQuery("FROM Achat a WHERE a.supplier.id = :idf", Achat.class);
        query.setParameter("idf", idf);
        return query.getResultList();
    }
}

