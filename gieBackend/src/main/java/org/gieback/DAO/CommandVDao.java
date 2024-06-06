package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Ventes;
import org.gieback.Entity.CommandV;
import org.gieback.Entity.EtatVentes;
import org.gieback.Entity.Ventes;
import org.gieback.HibernateUtility.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class CommandVDao implements ICommandeVDao{
    EntityManager entityManager = HibernateUtil.getEntityManager();
    @Override
    public List<Ventes> getAllVentes(int id) {
        CommandV c = entityManager.find(CommandV.class,id);
        return c.getVentes();
    }

    @Override
    public List<CommandV> getComByDate(LocalDate d) {
        String hql = "FROM CommandV c WHERE c.purchaseDate = :d";
        Query query = entityManager.createQuery(hql);
        query.setParameter("d", d);
        List<CommandV> c = query.getResultList();
        return c;
    }


    @Override
    public void validerComm(int id) {
        entityManager.getTransaction().begin();
        CommandV c = entityManager.find(CommandV.class,id);
        if (c.getE()== EtatVentes.In_Preparation){
            c.setE(EtatVentes.Delivered);
            entityManager.merge(c);
        }
        else {
            System.out.println("CommandV deja validee");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<CommandV> getByEtat(String e1) {
        String hql = "FROM CommandV c WHERE c.e = :e";
        Query query = entityManager.createQuery(hql);
        EtatVentes e = EtatVentes.valueOf(e1);
        query.setParameter("e", e);
        List<CommandV> c = query.getResultList();
        return c;
    }
    @Override
    public void deleteComm(int id) {
        entityManager.getTransaction().begin();
        CommandV c = entityManager.find(CommandV.class,id);
        if (c != null){
            c.setE(EtatVentes.Canceled);
            entityManager.merge(c);
        } else {
            System.out.println("id incorrect");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void addComm(CommandV c) {
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
    public List<CommandV> getAllCom() {
        return entityManager.createQuery("FROM CommandV" ,CommandV.class).getResultList();
    }

    @Override
    public void pendComm(int id) {
        entityManager.getTransaction().begin();
        CommandV c = entityManager.find(CommandV.class,id);
        if (c.getE()== EtatVentes.In_Preparation){
            c.setE(EtatVentes.Pending);
            entityManager.merge(c);
        }
        else {
            System.out.println("CommandV deja validee");
        }
        entityManager.getTransaction().commit();

    }

}
