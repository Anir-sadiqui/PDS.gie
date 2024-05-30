package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@JsonTypeName("personne")

public class Personne extends Contact implements Serializable {
//    public Personne(String phone, String email, Adresse adresse, String nom, String prenom , ContactType contactType) {
//        super(phone, email, adresse , contactType);
//        this.nom = nom;
//        this.prenom = prenom;
//    }

    public Personne(String phone, String email, Adresse adresse, String nom, String prenom){
        super(phone, email, adresse );
        this.nom = nom;
        this.prenom = prenom;
    }

    @Column(name = "nom")

    private String nom;

    @Column(name = "prenom")

    private String prenom;

    public Personne() {

    }
    @JsonCreator
    public Personne(
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("adresse") Adresse adresse,
            @JsonProperty("nom") String nom,
            @JsonProperty("prenom") String prenom,
            @JsonProperty("contactType") ContactType contactType
    ) {
        super(phone, email, adresse, contactType);
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + " " + prenom + ", Type: " + getContactType() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Email: " + getEmail();
    }
}