package org.gieback.Service;

import org.gieback.DAO.ProductDao;
import org.gieback.Entity.Category;
import org.gieback.Entity.EtatStock;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    ProductDao pdao = new ProductDao();

    @Override
    public List<Product> isAvailable(String t) {
        return pdao.isAvailable(t);
    }

    @Override
    public void add(Product p) {
        pdao.add(p);

    }

    @Override
    public void modify(int id, Map<String, String> attributs) {
        pdao.modify(id,attributs);

    }

    @Override
    public List<Product> getbyCat(String cat) {
        return pdao.getbyCat(cat);
    }

    @Override
    public List<Product> getAllProd() {
        return pdao.getAllProd();
    }

    @Override
    public void deleteProduct(int id) {
        pdao.deleteProduct(id);
    }

    @Override
    public Product getByName(String nom) {
        return pdao.getByName(nom);
    }

    @Override
    public void ajoutQ(int nq, int id) {
        pdao.ajoutQ(nq,id);
    }

}
