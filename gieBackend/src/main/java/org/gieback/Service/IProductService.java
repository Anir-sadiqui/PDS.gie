package org.gieback.Service;
import org.gieback.Entity.Category;

import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {

//    List<Product> getbyCat(Category cat);

//    List<Product> getAll();
      void    add(Product p);

//    Product getById(int id);

//    List<Product>  getByModel(String model);

    void modify(int id, Map<String, String> attributs);

//    void delete(String name, String description, Category category, int q, double prix, Model model );


}
