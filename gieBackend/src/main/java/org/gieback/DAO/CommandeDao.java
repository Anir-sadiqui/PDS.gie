package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;
import org.gieback.HibernateUtility.HibernateUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CommandeDao implements ICommandeDao{
    EntityManager entityManager = HibernateUtil.getEntityManager();
    @Override
    public List<Achat> getAllachats(int id) {
        Commande c = entityManager.find(Commande.class,id);
        List<Achat> a = c.getAchats();
        return a;
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
        Commande c = entityManager.find(Commande.class,id);
        if (c.getE()== EtatCommande.En_Cours){
            c.setE(EtatCommande.Livre);
        }
        System.out.println("Commande deja validee");
    }

    @Override
    public List<Commande> getByEtat(EtatCommande e) {
        String hql = "FROM Commande c WHERE c.e = :e";
        Query query = entityManager.createQuery(hql);
        query.setParameter("e", e.name());
        List<Commande> c = query.getResultList();
        return c;
    }

    @Override
    public void deleteComm(int id) {
        String hql = "delete from Commande where id =:id";
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
    public void addComm(Commande c) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(c);
            // entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
