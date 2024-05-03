package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class AchatDetail implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Achat achat;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "Prix_Total")
    private double TotalPrice;

    public AchatDetail() {}

    public AchatDetail(Long id, Achat achat, Product product, int quantity, double TotalPrice) {
        this.achat = achat;
        this.product = product;
        this.quantity = quantity;
        this.TotalPrice = TotalPrice;
    }

    @Override
    public String toString() {
        return "Id: " + achat + '\n' +
                "Quantity: " + quantity + '\n' +
                "Total Price: " + TotalPrice + '\n';
    }
}
