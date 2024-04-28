package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class Entreprise  extends Contact implements Serializable {
    private String formeJuridique;

    private String raisonSocial;

    public Entreprise(String phone, String email, Adresse adresse,ContactType contactType,String formeJuridique, String raisonSocial) {
        super(phone, email,adresse , contactType);
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;

    }


    public Entreprise() {

    }
}
