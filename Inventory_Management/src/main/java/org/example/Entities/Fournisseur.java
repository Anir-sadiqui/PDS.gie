package org.example.Entities;

import javax.persistence.*;
import java.util.List;
import lombok.Data;

@Data

@Entity
@Table(name = "fournisseurs")
public class Fournisseur extends Contact {

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Achat> achats;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Fournisseur() {
        // Default constructor
    }

    public Fournisseur(String phone, String email, Adresse adresse) {
        super(phone, email, adresse);
    }

    // Getters and setters...
}
