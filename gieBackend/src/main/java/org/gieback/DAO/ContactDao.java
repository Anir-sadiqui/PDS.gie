package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import org.gieback.Entity.Contact;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;

public class ContactDao implements IContactDao {

    private EntityManager entityManager;


    public ContactDao() {
        entityManager = HibernateUtil.getEntityManger();
    }

    @Override
    public void create(String nom, String adresse, String telephone, String email, int code_postal) {
        entityManager.createQuery(" from Client ").getResultList();
    }



    @Override
    public List<Contact> getAll() {

        return entityManager.createQuery(" from Client ").getResultList();
    }



    @Override
   public void add(Contact client) {

    }


}

