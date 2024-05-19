package org.gieback.DAO;

import org.gieback.Entity.Category;
import org.gieback.Entity.EtatStock;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductDao {
    List<Product> isAvailable (String t);
    void add(Product p);
    void modify(int id, Map<String, String> attributs);
     List<Product> getbyCat(String cat);
     List<Product> getAllProd();
     void deleteProduct(int id);
     List<Product> getByName (String nom);



}
