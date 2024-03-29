package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;
import java.util.Map;

@Data
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

    @Override
    public List<Adresse> getByVille(String ville) {
        String hql = "FROM Adresse a WHERE a.ville = :ville";
        Query query = entityManager.createQuery(hql);
        query.setParameter("ville", ville);
        List<Adresse> r = query.getResultList();
        return r;
    }

    @Override
    public List<Adresse> getByQuartier(String quartier, String ville) {
        String hql = "FROM Adresse A WHERE A.quartier = :quartier AND A.ville = :ville";
        Query q = entityManager.createQuery(hql);
        q.setParameter("quartier", quartier);
        q.setParameter("ville", ville);
        List<Adresse> r = q.getResultList();
        return r;
    }

    @Override
    public void modifierAdresse(int id, Map<String, String> attributs) {
            entityManager.getTransaction().begin();
            Adresse adresse = entityManager.find(Adresse.class, id);
            if (adresse != null) {
                for (Map.Entry<String, String> entry : attributs.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    switch (key) {
                        case "ville":
                            adresse.setVille(value);
                            break;
                        case "quartier":
                            adresse.setQuartier(value);
                            break;
                        case "numero":
                            adresse.setNumero(value);
                            break;

                    }
                }
                entityManager.merge(adresse);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }




    @Override
    public List<Adresse> sortByVille(String ordre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Adresse> cq = cb.createQuery(Adresse.class);
        Root<Adresse> entrepriseRoot = cq.from(Adresse.class);

        if ("asc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.asc(entrepriseRoot.get("ville")));
        } else if ("desc".equalsIgnoreCase(ordre)) {
            cq.orderBy(cb.desc(entrepriseRoot.get("ville")));
        } else {
            throw new IllegalArgumentException("Ordre de tri non reconnu. Utilisez 'asc' ou 'desc'.");
        }

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void deleteById(int id) {
        String hql = "delete from Adresse where id =:id";
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




}
