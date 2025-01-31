package org.gieback.DAO;

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
     Product getByName (String nom);
     void ajoutQ (int nq, int id);
    void retirerQ(int q, int id);



}
