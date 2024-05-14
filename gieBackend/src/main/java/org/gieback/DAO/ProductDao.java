package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public class ProductDao implements IProductDao{
    EntityManager entityManager;
    @Override
    public boolean isAvailable(int i) {
        Product p = entityManager.find(Product.class, i);
        if (p.getQ()>0){
            return true;
        }
        return false;
    }

    @Override
    public void add(Product pe) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pe);
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
    public List<Product> getByName(String na) {
        String requet = "FROM Product p WHERE p.name = :na";
        Query query = entityManager.createQuery(requet);
        query.setParameter("na", na);
        List<Product> q = query.getResultList();
        return q;
    }
    @Override
    public List<Product> getbyCat(Category ca) {
        String hql = "FROM Product p WHERE p.category = :cat";
        Query query = entityManager.createQuery(hql);
        query.setParameter("cat", ca);
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
    public List<Product> getByName(String nom) {
        String hql = "FROM Product p WHERE p.name = :nom";
        Query query = entityManager.createQuery(hql);
        query.setParameter("nom", nom);
        List<Product> p = query.getResultList();
        return p;
    }


}
