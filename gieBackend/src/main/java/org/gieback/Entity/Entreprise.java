package org.gieback.Entity;

import jakarta.persistence.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@DiscriminatorValue("entreprise")
public class Entreprise extends Contact  implements Serializable {
    @Column(name = "formeJuridique")

    private String formeJuridique;
    @Column(name = "raisonSocial", unique = true)
    private String raisonSocial;

    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial , ContactType contactType) {
        super(phone, email,adresse , contactType );
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;
    }
    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial ){
        super(phone, email,adresse );
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;
    }

    public Entreprise() {

    }

    @Override
    public String toString() {
        return "Raison Social: " + raisonSocial + ", Type: " + getContactType() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Forme Juridique: " + formeJuridique;
    }

}