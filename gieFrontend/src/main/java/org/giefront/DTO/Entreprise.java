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

    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial ){
        super(phone, email,adresse );
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public Entreprise() {

    }
}
