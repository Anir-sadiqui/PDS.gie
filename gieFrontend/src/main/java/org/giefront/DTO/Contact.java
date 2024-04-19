package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;

@Data



public class Contact implements Serializable{

    public Contact(String phone, String email, Adresse adresse) {
        this.phone = phone;
        this.email = email;
        this.adresse = adresse;
    }


    private Long id;

    private String phone;


    private String email;

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    private Adresse adresse;

    @Override
    public String toString() {
        return this.getAdresse().getAdresse_id()+"";
    }

    public Contact() {

    }


}