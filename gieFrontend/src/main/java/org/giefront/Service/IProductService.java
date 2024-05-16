package org.giefront.Service;

import org.giefront.DTO.Category;
import org.giefront.DTO.Product;

import java.util.List;

public interface IProductService extends IService<Product> {


        public void deleteProduct(int idProduct);
        public List<Product> getbyname(String name);
        public List<Product> getbyCat(Category cat);


//        int countOccupationByService(int idservice, boolean occupation);
        void update(int id,String name, String description, Category category,int quantite, Double prix);
}
