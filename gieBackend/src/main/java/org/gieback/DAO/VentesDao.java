package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;
import org.gieback.Entity.Commande;
import org.gieback.HibernateUtility.HibernateUtil;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.PersonneService;

import java.util.List;
import java.util.Map;

public class VentesDao implements IVentesDao {

    private EntityManager entityManager;

    public VentesDao() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public List<Ventes> getAll(CommandV c) {
        Query query = entityManager.createQuery(" FROM Ventes a WHERE a.c.id = : c", Ventes.class);
        query.setParameter("c", c);
        return  query.getResultList();
    }

    @Override
    public void add(Ventes p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            System.out.println(p.getDetails());

            if (p.getDetails() != null) {
                // Utiliser merge au lieu de persist pour gérer les entités détachées
                p.setDetails(entityManager.merge(p.getDetails()));
            }

            // Utiliser merge au lieu de persist pour l'entité Ventes
            entityManager.merge(p);

            transaction.commit();
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
            Ventes Ventes = entityManager.find(Ventes.class, id);
            if (Ventes != null) {
                entityManager.remove(Ventes);
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
        Ventes p = entityManager.find(Ventes.class, id);
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
                    case "ClientE":
                        EntrepriseService es = new EntrepriseService();
                        p.setSupplier(es.getEnterpriseById(Integer.parseInt(value)));
                        break;
                    case "ClientP":
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
    public List<Ventes> chercherParFournisseur(int idf) {
        Query query = entityManager.createQuery("FROM Ventes a WHERE a.supplier.id = :idf", Ventes.class);
        query.setParameter("idf", idf);
        return query.getResultList();
    }

    @Override
    public List<Ventes> getByCommande(int idC) {
        Query query = entityManager.createQuery("FROM Ventes a WHERE a.c.id = :idC", Ventes.class);
        query.setParameter("idC",idC);
        return query.getResultList();
    }
}
