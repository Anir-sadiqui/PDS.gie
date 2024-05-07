package org.gieback.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;
import org.gieback.HibernateUtility.HibernateUtil;

import java.util.List;
import java.util.Map;

public class ProductDao implements IProductDao {


    private EntityManager entityManager;

    public ProductDao() {
        entityManager = HibernateUtil.getEntityManger();
    }
//    @Override
//    public List<Product> getbyCat(Category cat) {
//        String hql = "FROM Product p WHERE p.category = :cat";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("cat", cat);
//        List<Product> p = query.getResultList();
//            return p;
//            }




//    @Override
////    public List<Product> getAll() {
//
//        return entityManager.createQuery("From Product ", Product.class).getResultList();
//    }


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

//    @Override
//    public Product getById(int id) {
//        return null;
//    }

//    @Override
//    public List<Product>  getByModel(String model) {
//        String hql = "FROM Product p WHERE p.model= :model";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("model", model);
//        List<Product> p = query.getResultList();
//        return p;
//    }

    @Override
    public void modify(int id, Map<String, String> attributs) {

    }

//    @Override
//    public void delete(String name, String description, Category category, int q, double prix, Model model) {
//
//    }


}
