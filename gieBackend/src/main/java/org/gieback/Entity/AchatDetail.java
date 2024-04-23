package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class AchatDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Achat achat;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    public AchatDetail() {}

    public AchatDetail(Long id, Achat achat, Product product, int quantity, double unitPrice) {
        this.id = id;
        this.achat = achat;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Quantity: " + quantity + '\n' +
                "Unit Price: " + unitPrice + '\n';
    }
}
