package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class Entreprise  extends Contact implements Serializable {

    private Long id;

    private String phone;
    private String email;

    private String formeJuridique;

    private String raisonSocial;

    public Entreprise(String phone, String email, Adresse adresse,ContactType contactType,String img ,String formeJuridique, String raisonSocial) {
        super(phone, email,adresse , contactType,img);
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  raisonSocial ;
    }

    public Entreprise() {

    }
}
