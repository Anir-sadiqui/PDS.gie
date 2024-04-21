package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.gieback.Entity.*;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;
import java.util.Map;

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
            System.out.println(p.getAdresse());
            if (p.getAdresse() != null ) {
                entityManager.persist(p.getAdresse());
                p.setAdresse(p.getAdresse());
            }
            entityManager.persist(p);
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

    @Override
    public List<Personne> getByNom(String n) {
        String hql = "FROM Personne p WHERE p.nom = :nom";
        Query query = entityManager.createQuery(hql);
        query.setParameter("nom", n);
        List<Personne> r = query.getResultList();
        return r;
    }

    @Override
    public List<Personne> getByPrenom(String pre) {
        String hql = "FROM Personne p WHERE p.prenom = :prenom";
        Query query = entityManager.createQuery(hql);
        query.setParameter("prenom", pre);
        List<Personne> r = query.getResultList();
        return r;
    }

    @Override
    public List<Personne> sortByNom(String ordre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
        Root<Personne> personneRoot = cq.from(Personne.class);
        if ("asc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.asc(personneRoot.get("nom")));
        } else if ("desc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.desc(personneRoot.get("nom")));
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu. Utilisez 'asc' ou 'desc'.");
        }

        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public List<Personne> sortById(String ordre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
        Root<Personne> personneRoot = cq.from(Personne.class);
        if ("asc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.asc(personneRoot.get("id")));
        } else if ("desc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.desc(personneRoot.get("id")));
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu. Utilisez 'asc' ou 'desc'.");
        }

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void modifier(String id, Map<String, String> attributs) {
        entityManager.getTransaction().begin();
        Personne p = entityManager.find(Personne.class, id);
        if (p != null) {
            for (Map.Entry<String, String> entry : attributs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "Prenom":
                        p.setPrenom(value);
                        break;
                    case "Nom":
                        p.setNom(value);
                        break;
                    case "phone":
                        p.setPhone(value);
                        break;
                    case "email":
                        p.setEmail(value);
                        break;
                }
            }
            entityManager.merge(p);
        }
        else { System.out.println("id incorrect");}
        entityManager.getTransaction().commit();


    }



    @Override
    public void deleteById(int id) {
        String hql = "delete from Personne where id =:id";
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
    public Personne getByEmail(String email) {
        String hql = "FROM Personne p WHERE p.email = :email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        Personne r = (Personne) query.getSingleResult();
        return r;
    }

}


