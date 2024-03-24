package org.gieback.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="Personne")

public class Personne extends Contact implements Serializable {
    public Personne(String phone, String email,Adresse adresse, String nom, String prenom) {
        super(phone, email,adresse);
        this.nom = nom;
        this.prenom = prenom;
    }

    @Column(name = "nom")

    private String nom;
    @Column(name = "prenom")

    private String prenom;

    public Personne() {

    }

    // Getters and setters
}
