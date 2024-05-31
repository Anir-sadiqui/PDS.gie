package org.giefront.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class Personne extends Contact implements Serializable {
    private String nom;
    private String prenom;

    public Personne(String phone, String email, Adresse adresse, ContactType contactType,String img,String nom, String prenom) {
        super(phone, email, adresse , contactType,img);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String phone, String email, Adresse adresse,String img, String nom, String prenom){
        super(phone, email, adresse,img );
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Personne() {

    }

    @Override
    public String toString() {
        return  nom + '\''+ prenom + '\'' ;

    }
}
