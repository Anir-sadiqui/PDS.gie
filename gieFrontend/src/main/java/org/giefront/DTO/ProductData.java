package org.giefront.DTO;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "High-end gaming laptop", Category.ELECTRONICS, 10, 1500.00));
        products.add(new Product("Smartphone", "Latest model smartphone", Category.ELECTRONICS, 25, 799.99));
        products.add(new Product("T-Shirt", "Cotton t-shirt", Category.CLOTHING, 50, 19.99));
        products.add(new Product("Jeans", "Denim jeans", Category.CLOTHING, 40, 49.99));
        products.add(new Product("Apple", "Fresh red apple", Category.FOOD, 100, 0.99));
        products.add(new Product("Bread", "Whole grain bread", Category.FOOD, 30, 2.99));
        return products;
    }
}
