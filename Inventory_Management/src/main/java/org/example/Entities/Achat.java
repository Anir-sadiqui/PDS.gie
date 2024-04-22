package org.example.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "achats")
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AchatDetail> details;

    private Double total;

    public Achat() {
        // Default constructor
    }

    public Achat(Date date, Fournisseur fournisseur, Double total) {
        this.date = date;
        this.fournisseur = fournisseur;
        this.total = total;
    }

    // Getters and setters...
}

