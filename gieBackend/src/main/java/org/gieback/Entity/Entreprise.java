package org.gieback.Entity;

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
@DiscriminatorValue("entreprise")
public class Entreprise extends Contact  implements Serializable {
    @Column(name = "formeJuridique")

    private String formeJuridique;
    @Column(name = "raisonSocial")

    private String raisonSocial;

    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial) {
        super(phone, email,adresse);
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;

    }

    public Entreprise() {

    }

    @Override
    public String toString() {
        return  "Raison social: " + raisonSocial + '\n' +
                "phone: " + getPhone() + '\n' +
                "email: " + getEmail() + '\n' +
                "Forme juridique: " + formeJuridique + '\n' ;

    }

}