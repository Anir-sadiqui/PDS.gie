package org.example.Entities;

import javax.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public Inventory() {
        // Default constructor
    }

    public Inventory(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and setters...
}
