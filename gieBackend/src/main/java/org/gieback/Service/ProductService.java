package org.gieback.Service;

import org.gieback.DAO.EntrepriseDao;
import org.gieback.DAO.IEntrepriseDao;
import org.gieback.DAO.IProductDao;
import org.gieback.DAO.ProductDao;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    IProductDao productDao = new ProductDao();
//    @Override
//    public List<Product> getbyCat(Category cat) {
//        return null;
//    }





//    @Override
//    public Product getById(int id) {
//        return null;
//    }

//    @Override
//    public List<Product> getByModel(String model) {
//        return null;
//    }

    @Override
    public void add(Product p) {
        productDao.add(p);
    }

    @Override
    public void modify(int id, Map<String, String> attributs) {
        productDao.modify(id,attributs);
    }

//    @Override
//    public void delete(String name, String description, Category category, int q, double prix, Model model) {
//
//    }
}
