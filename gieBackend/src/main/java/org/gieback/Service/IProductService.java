package org.gieback.Service;

import org.gieback.Entity.Category;
import org.gieback.Entity.EtatStock;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface  IProductService {
    List<Product> isAvailable(String t);
    void add(Product p);
    void modify(int id, Map<String, String> attributs);
    List<Product> getbyCat(String cat);
    List<Product> getAllProd();
    void deleteProduct(int id);
    Product getByName (String nom);

}
