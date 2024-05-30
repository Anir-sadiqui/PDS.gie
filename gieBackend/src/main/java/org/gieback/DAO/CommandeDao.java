package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;
import org.gieback.HibernateUtility.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class CommandeDao implements ICommandeDao{
    EntityManager entityManager = HibernateUtil.getEntityManager();
    @Override
    public List<Achat> getAllachats(int id) {
        Commande c = entityManager.find(Commande.class,id);
        return c.getAchats();
    }

    @Override
    public List<Commande> getComByDate(LocalDate d) {
        String hql = "FROM Commande c WHERE c.purchaseDate = :d";
        Query query = entityManager.createQuery(hql);
        query.setParameter("d", d);
        List<Commande> c = query.getResultList();
        return c;
    }

    @Override
    public void validerComm(int id) {
        entityManager.getTransaction().begin();
        Commande c = entityManager.find(Commande.class,id);
        if (c.getE()== EtatCommande.In_Preparation){
            c.setE(EtatCommande.Delivered);
        }
        else if (c.getE()== EtatCommande.Initialised) {
            c.setE(EtatCommande.In_Preparation);
            entityManager.merge(c);
        }
        else {
            System.out.println("Commande deja validee");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Commande> getByEtat(String e1) {
        String hql = "FROM Commande c WHERE c.e = :e";
        Query query = entityManager.createQuery(hql);
        EtatCommande e = EtatCommande.valueOf(e1);
        query.setParameter("e", e);
        List<Commande> c = query.getResultList();
        return c;
    }
    @Override
    public void deleteComm(int id) {
        entityManager.getTransaction().begin();
        Commande c = entityManager.find(Commande.class,id);
        if (c != null){
            c.setE(EtatCommande.Canceled);
            entityManager.merge(c);
        } else {
            System.out.println("id incorrect");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void addComm(Commande c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(c);
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
    public List<Commande> getAllCom() {
        return entityManager.createQuery("FROM Commande" ,Commande.class).getResultList();
    }

}
