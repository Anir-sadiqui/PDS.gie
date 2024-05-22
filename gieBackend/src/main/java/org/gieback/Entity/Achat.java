package org.gieback.Entity;


import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

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

    public Achat( AchatDetail details, Commande c, Contact supplier ) {
        this.purchaseDate = LocalDate.now();
        this.details=details;
        this.c=c;
        this.supplier=supplier;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Purchase Date: " + purchaseDate + '\n'+
                "Details : " + "\n" + details.toString();
    }


}

