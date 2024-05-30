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
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "Prix_Total")
    private double TotalPrice;

    public AchatDetail() {}

    public AchatDetail( Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = product.getPrix() * quantity;
    }

    @Override
    public String toString() {
        return "Category: " + product.getCategory() + "\n"+
                "Quantity: " + quantity + '\n' +
                "Total Price: " + TotalPrice + '\n';
    }
}
