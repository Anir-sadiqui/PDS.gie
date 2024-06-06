package org.gieback.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
public class CommandV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ventes")
    private List<Ventes> ventes;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "Etat_Commande")
    private EtatVentes e;

    public CommandV(){}

    public CommandV(List<Ventes> ventes) {
        this.ventes = ventes;
        this.purchaseDate = LocalDate.now();
        this.e=EtatVentes.In_Preparation;
    }
}
