package org.example.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@DiscriminatorValue("personne")

public class Personne extends Contact implements Serializable {
    public Personne(String phone, String email, Adresse adresse, String nom, String prenom) {
        super(phone, email, adresse);
        this.nom = nom;
        this.prenom = prenom;

    }

    @Column(name = "nom")

    private String nom;
    @Column(name = "prenom")

    private String prenom;

    public Personne() {

    }

    @Override
    public String toString() {
        return "nom: " + nom + " " + prenom + '\n' +
                "phone: " + getPhone() + '\n' +
                "email: " + getEmail() ;
    }
}