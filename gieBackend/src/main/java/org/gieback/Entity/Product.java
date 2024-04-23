package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

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

    public Product() {}

    public Product(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + '\n' +
                "Name: " + name + '\n' +
                "Description: " + description + '\n' +
                "Category: " + category.name() + '\n';
    }

    public enum Category {
        MOBILE_DEVICES,
        COMPUTERS,
        HOME_ELECTRONICS,
        AUDIO_VIDEO,
        WEARABLES,
        OTHER_ELECTRONICS
    }
}
