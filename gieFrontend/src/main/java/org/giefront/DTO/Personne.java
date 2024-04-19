package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;

@Data

public class Personne extends Contact implements Serializable {
    public Personne(String phone, String email, Adresse adresse, String nom, String prenom) {
        super(phone, email, adresse);
        this.nom = nom;
        this.prenom = prenom;

    }


    private String nom;

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