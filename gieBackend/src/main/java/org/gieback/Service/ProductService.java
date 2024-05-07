package org.gieback.Service;

import org.gieback.DAO.ProductDao;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    ProductDao pdao = new ProductDao();

    @Override
    public boolean isAvailable(int id) {
        return pdao.isAvailable(id);
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
    public List<Product> getbyCat(Category cat) {
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
}
