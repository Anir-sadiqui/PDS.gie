package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING) // Ensures enum values are stored as text in the database
    @Column(name = "category")
    private Category category;


    @Column(name = "Quantite_stock")
    private int q;

    @Column(name ="Prix_unitaire")
    private double prix;

    private String imagePath;



    public Product() {}

    public Product(String name, String description, Category category,double prix, String imagePath ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.q=0;
        this.prix=prix;
        this.imagePath=imagePath;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + '\n' +
                "Name: " + name + '\n' +
                "Description: " + description + '\n' +
                "Category: " + category.name() + '\n'+
                "Quantite en stock: " + q + '\n'+
                "Prix unitaire: " + prix;
    }
}
