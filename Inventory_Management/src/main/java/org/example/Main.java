package org.example;

import org.example.HibernateUtility.HibernateUtil;
import org.example.Entities.Category;
import org.example.Entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        // Get the EntityManager
        EntityManager entityManager = HibernateUtil.getEntityManger();

        // Start a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Example: Create and persist a Category
            Category category = new Category();
            category.setName("Electronics");
            entityManager.persist(category);

            // Example: Create and persist a Product
            Product product = new Product();
            product.setName("Smartphone");
            product.setCategory(category); // Associate with the created category
            entityManager.persist(product);

            // Commit the transaction
            transaction.commit();

            System.out.println("Successfully persisted Category and Product!");

        } catch (Exception e) {
            // If something goes wrong, rollback the transaction
            transaction.rollback();
            System.err.println("An error occurred during transaction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
