package org.example.Entities;

import javax.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "achat_details")
public class AchatDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "achat_id")
    private Achat achat;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private Double price;

    public AchatDetail() {
        // Default constructor
    }

    public AchatDetail(Achat achat, Product product, Integer quantity, Double price) {
        this.achat = achat;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters...
}

