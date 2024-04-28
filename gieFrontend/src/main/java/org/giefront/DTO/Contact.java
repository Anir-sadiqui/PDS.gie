package org.giefront.DTO;

import lombok.Data;

import java.io.Serializable;


@Data

public class Contact implements Serializable {
    private Long id;

    private String phone;

    private String email;

    private Adresse adresse;

    private ContactType contactType ;

    public Contact(String phone, String email, Adresse adresse, ContactType contactType) {
        this.phone = phone;
        this.email = email;
        this.adresse = adresse;
        this.contactType = contactType ;
    }
    public Contact(){

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
