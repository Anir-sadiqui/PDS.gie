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
    public void create(Contact contact) {
        entityManager.createQuery(" from Contact ").getResultList();
    }


    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public void add(Contact client) {

    }


}
