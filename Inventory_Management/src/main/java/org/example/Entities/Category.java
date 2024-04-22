package org.example.Entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import lombok.Data;

@Data

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Category() {
        // Default constructor
    }

    public Category(String name) {
        this.name = name;
    }

    // Getters and setters...
}

