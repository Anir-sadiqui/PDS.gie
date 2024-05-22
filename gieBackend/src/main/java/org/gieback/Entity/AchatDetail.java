package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class AchatDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private Achat achat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "Prix_Total")
    private double TotalPrice;

    public AchatDetail() {}

    public AchatDetail(Achat achat, Product product, int quantity) {
        this.achat = achat;
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = product.getPrix() * quantity;
    }

    @Override
    public String toString() {
        return "Product: " + achat + '\n' +
                "Category: " + product.getCategory() + "\n"+
                "Quantity: " + quantity + '\n' +
                "Total Price: " + TotalPrice + '\n';
    }
}
