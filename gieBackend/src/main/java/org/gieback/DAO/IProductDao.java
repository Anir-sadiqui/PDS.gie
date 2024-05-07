package org.gieback.DAO;

import org.gieback.Entity.Category;
import org.gieback.Entity.Model;
import org.gieback.Entity.Product;

import java.util.List;
import java.util.Map;

public interface IProductDao {

    void    add(Product p);

    void modify(int id, Map<String, String> attributs);


    }

