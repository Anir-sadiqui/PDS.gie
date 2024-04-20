package org.giefront.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class Personne extends Contact implements Serializable {
    private String nom;

    private String prenom;


    public Personne(String phone, String email, Adresse adresse, String nom, String prenom) {
        super(phone, email, adresse);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne() {

    }
}
