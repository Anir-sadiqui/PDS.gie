package org.gieback.Service;

import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface  IProductService {
    boolean isAvailable (int id);
    void add(Product p);
    void modify(int id, Map<String, String> attributs);
    List<Product> getbyCat(Category cat);
    List<Product> getAllProd();
    void deleteProduct(int id);

}
