package org.gieback.DAO;

import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductDao {
    boolean isAvailable (int id);
    void add(Product p);
    void modify(int id, Map<String, String> attributs);
     List<Product> getbyCat(Category cat);
     List<Product> getAllProd();
     void deleteProduct(int id);
     List<Product> getByName (String nom);



}
