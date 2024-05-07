package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public class ProductDao implements IProductDao{
    EntityManager entityManager;
    @Override
    public boolean isAvailable(int id) {
        Product p = entityManager.find(Product.class, id);
        if (p.getQ()>0){
            return true;
        }
        return false;
    }

    @Override
    public void add(Product p) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(p);
            transaction.commit();

        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void modify(int id, Map<String, String> attributs) {
        entityManager.getTransaction().begin();
        Product E = entityManager.find(Product.class, id);
        if (E != null) {
            for (Map.Entry<String, String> entry : attributs.entrySet()) {
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
    public List<Product> getbyCat(Category cat) {
        String hql = "FROM Product p WHERE p.category = :cat";
        Query query = entityManager.createQuery(hql);
        query.setParameter("cat", cat);
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


}
