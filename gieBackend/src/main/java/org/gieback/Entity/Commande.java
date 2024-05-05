package org.gieback.Entity;


import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "Achats")
    private List<Achat> achats;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "Etat_Commande")
    private EtatCommande e;

    public Commande(){}

    public Commande(List<Achat> achats, Date purchaseDate, EtatCommande e) {
        this.achats = achats;
        this.purchaseDate = purchaseDate;
        this.e=e;
    }

}
