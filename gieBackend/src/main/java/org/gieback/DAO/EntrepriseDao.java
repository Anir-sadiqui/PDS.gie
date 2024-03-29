package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;
import java.util.Map;

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

    @Override
    public void modifier(int id, Map<String, String> attributs) {
        entityManager.getTransaction().begin();
        Entreprise E = entityManager.find(Entreprise.class, id);
        if (E != null) {
            for (Map.Entry<String, String> entry : attributs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "formeJuridique":
                        E.setFormeJuridique(value);
                        break;
                    case "raisonSocial":
                        E.setRaisonSocial(value);
                        break;
                    case "phone":
                        E.setPhone(value);
                        break;
                    case "email":
                        E.setEmail(value);
                        break;

                }
            }
            entityManager.merge(E);
        }
        else { System.out.println("id incorrect");}
            entityManager.getTransaction().commit();
            entityManager.close();


    }
    @Override
    public void deleteByid(int id) {
        String hql = "delete from Entreprise where id =:id";
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);

        }
    }



    @Override
    public List<Entreprise> sortById(String ordre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entreprise> cq = cb.createQuery(Entreprise.class);
        Root<Entreprise> EntrepriseRoot = cq.from(Entreprise.class);
        if ("asc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.asc(EntrepriseRoot.get("id")));
        } else if ("desc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.desc(EntrepriseRoot.get("id")));
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu. Utilisez 'asc' ou 'desc'.");
        }

        return entityManager.createQuery(cq).getResultList();
    }

}
