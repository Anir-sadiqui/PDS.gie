package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Category;
import org.gieback.Entity.EtatStock;
import org.gieback.Entity.Product;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao implements IProductDao{
    EntityManager entityManager = HibernateUtil.getEntityManger();
    @Override
    public  List<Product> isAvailable(String t) {
        List<Product> p = new ArrayList<>();
        if (t.equals(EtatStock.Available.name())){
            String hql = "FROM Product WHERE q>0";
            Query query = entityManager.createQuery(hql);
             p = query.getResultList();
        }
        else if (t.equals(EtatStock.Out_of_Stock.name())) {
            String hql = "FROM Product WHERE q=0";
            Query query = entityManager.createQuery(hql);
             p = query.getResultList();
        }

        return p;
    }

    @Override
    public void add(Product pe) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pe);
            entityManager.flush();
            transaction.commit();

        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void modify(int id, Map<String, String> attribut) {
        entityManager.getTransaction().begin();
        Product E = entityManager.find(Product.class, id);
        if (E != null) {
            for (Map.Entry<String, String> entry : attribut.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "Name":
                        E.setName(value);
                        break;
                    case "Category":
                        E.setCategory(Category.valueOf(value));
                        break;
                    case "Description":
                        E.setDescription(value);
                        break;
                    case "Price":
                        E.setPrix(Double.parseDouble(value));
                        break;

                }
            }
            entityManager.merge(E);
        } else {
            System.out.println("id incorrect");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Product> getbyCat(String ca) {
        String hql = "FROM Product p WHERE p.category = :cat";
        Query query = entityManager.createQuery(hql);
        query.setParameter("cat", Category.valueOf(ca));
        List<Product> p = query.getResultList();
        return p;
    }

    @Override
    public List<Product> getAllProd() {
        return entityManager.createQuery("From Product ", Product.class).getResultList();
    }

    @Override
    public void deleteProduct(int id) {
        String hql = "delete from Product where id =:id";
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
    public Product getByName(String nom) {
        String hql = "FROM Product p WHERE p.name = :nom";
        Query query = entityManager.createQuery(hql);
        query.setParameter("nom", nom);
        Product p = (Product) query.getSingleResult();
        return p;
    }


}
