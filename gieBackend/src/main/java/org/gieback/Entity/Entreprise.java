package org.gieback.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="Entreprise")
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
// Getters and setters
}
