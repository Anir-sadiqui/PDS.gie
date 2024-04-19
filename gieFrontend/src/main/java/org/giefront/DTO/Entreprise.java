package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class Entreprise extends Contact  implements Serializable {

    private String formeJuridique;

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