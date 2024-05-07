package org.gieback.Entity;


import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToOne
    @JoinColumn(name = "Details_achats")
    private AchatDetail details ;

    @ManyToOne
    @JoinColumn(name = "Commande")
    private  Commande c;



    public Achat() {}

    public Achat(Date purchaseDate, Contact supplier, AchatDetail details, Commande c ) {
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
        this.details=details;
        this.c=c;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Purchase Date: " + purchaseDate + '\n';
    }


}

